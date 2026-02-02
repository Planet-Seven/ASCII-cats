package views.REST

import cask.*
import converters.ASCIIConverterImpl
import exporters.{StringExporter, StringExporterSubscriber}
import filters.BrightnessFilter
import generator.{ASCIIArtGenerator, GeneratorArguments}
import loaders.CatAPILoader

object RESTView extends MainRoutes {
  @cask.get("/cat")
  def get(width: Option[Int] = None,
          brightness: Option[Int] = None,
          invert: Option[Boolean] = None,
          conversionTable: Option[String] = None,
          customTable: Option[String] = None): String = {

    val result: StringExporterSubscriber = StringExporterSubscriber()
    val exporter: StringExporter = StringExporter()
    exporter.subscribe(result)

    val generatorArguments = GeneratorArguments(
      _imageLoader = CatAPILoader(width.getOrElse(120)),
      _filters = Seq(),
      _ascii_converter = ASCIIConverterImpl(),
      _exporters = Seq(exporter)
    )

    brightness match
      case Some(value) => generatorArguments.filters = generatorArguments.filters :+ BrightnessFilter(value/255.0)
      case None =>

    val generator = ASCIIArtGenerator()
    generator.execute(generatorArguments)

    result.res
  }
  initialize()
}