package views.REST

import cask.*
import converters.ASCIIConverterImpl
import exporters.{StringExporterSubscriber, StringExporter}
import generator.{ASCIIArtGenerator, GeneratorArguments}
import loaders.CatAPILoader

object RESTView extends MainRoutes {
  @cask.post("/cat")
  def post(request: Request): String = {

    val result: StringExporterSubscriber = StringExporterSubscriber()
    val exporter: StringExporter = StringExporter()
    exporter.subscribe(result)

    val generatorArguments = GeneratorArguments(
      _imageLoader = CatAPILoader(),
      _filters = Seq(),
      _ascii_converter = ASCIIConverterImpl(),
      _exporters = Seq(exporter)
    )

    val generator = ASCIIArtGenerator()
    generator.execute(generatorArguments)

    result.res
  }
  initialize()
}