package generator

import converters.{ASCIIConverter, GrayscaleConvertor}
import exporters.Exporter
import filters.Filter
import loaders.ImageLoader

class GeneratorArguments(
                          _imageLoader : ImageLoader,
                          _filters : Seq[Filter],
                          _ascii_converter : ASCIIConverter,
                          _exporters : Seq[Exporter]) {
  var imageLoader: ImageLoader = _imageLoader
  var filters: Seq[Filter] = _filters
  var ascii_converter: ASCIIConverter = _ascii_converter
  var exporters: Seq[Exporter] = _exporters
}