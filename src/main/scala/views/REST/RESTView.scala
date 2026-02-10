package views.REST

import cask.*
import conversionTables.{ArrayConversionTable, BourkeConversionTable, LongBourkeConversionTable}
import converters.ASCIIConverterImpl
import exporters.{StringExporter, StringExporterSubscriber}
import filters.{BrightnessFilter, FlipFilter, InvertFilter}
import generator.{ASCIIArtGenerator, GeneratorArguments}
import loaders.CatAPILoader

object RESTView extends MainRoutes {
  override def port: Int =
    sys.env.getOrElse("PORT", "8080").toInt

  override def host: String =
    "0.0.0.0"

  def parseArguments(width: Option[Int] = None,
                             brightness: Option[Int] = None,
                             invert: Option[Boolean] = None,
                             flip: Option[String] = None,
                             conversionTable: Option[String] = None,
                             customTable: Option[String] = None): Either[List[String], GeneratorArguments] = {
    var errors: List[String] = List()
    val exporter: StringExporter = StringExporter()

    val generatorArguments = GeneratorArguments(
      _imageLoader = CatAPILoader(120),
      _filters = Seq(),
      _ascii_converter = ASCIIConverterImpl(),
      _exporters = Seq(exporter)
    )

    if conversionTable.nonEmpty && customTable.nonEmpty then
      errors :+= "Cannot specify both conversionTable and customTable."

    width.foreach { value =>
      if value <= 10 || value > 1920 then errors :+= "Width must be in [10,1920]."
      else generatorArguments.imageLoader = CatAPILoader(value.abs)
    }
    brightness.foreach { value =>
      if value < -255 || value > 255 then errors :+= "Brightness must be in [-255,255]."
      else generatorArguments.filters :+= BrightnessFilter(value / 255.0)
    }
    invert.foreach {
      case true => generatorArguments.filters :+= InvertFilter()
      case false => // do nothing
    }
    flip.foreach {
      case "x" => generatorArguments.filters :+= FlipFilter(flipX = true)
      case "y" => generatorArguments.filters :+= FlipFilter(flipX = false)
      case value => errors :+= s"Invalid flip axes: $value."
    }
    conversionTable.foreach {
      case "Bourke" => generatorArguments.ascii_converter = ASCIIConverterImpl(conversionTable = BourkeConversionTable())
      case "LongBourke" => generatorArguments.ascii_converter = ASCIIConverterImpl(conversionTable = LongBourkeConversionTable())
      case value => errors :+= s"Invalid conversion table: $value."
    }
    customTable.foreach {
      case "" => errors :+= "Custom conversion table cannot be empty."
      case value => generatorArguments.ascii_converter = ASCIIConverterImpl(conversionTable = ArrayConversionTable(value.toCharArray))
    }

    if errors.nonEmpty then Left(errors)
    else Right(generatorArguments)
  }

  @cask.get("")
  def root() = cask.Redirect("/ui/")

  @cask.staticResources("/ui")
  def webui() = "web-ui"

  @cask.get("/cat")
  def get(width: Option[Int] = None,
          brightness: Option[Int] = None,
          invert: Option[Boolean] = None,
          flip: Option[String] = None,
          conversionTable: Option[String] = None,
          customTable: Option[String] = None): String = {

    val generatorArguments = parseArguments(width, brightness, invert, flip, conversionTable, customTable)

    generatorArguments.fold(
      errors => errors.mkString("\n"),
      arguments => {
        val result = StringExporterSubscriber()
        arguments.exporters.collect { case e: StringExporter => e }
          .foreach(_.subscribe(result))
        ASCIIArtGenerator().execute(arguments)
        result.res
      }
    )
  }
  initialize()
}