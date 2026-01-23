package generator

import converters.ASCIIConverter
import exporters.Exporter
import filters.Filter
import loaders.ImageLoader

class GeneratorArguments(
                          _imageLoader : ImageLoader,
                          _filters : Seq[Filter[Double]],
                          _converter : ASCIIConverter,
                          _exporters : Seq[Exporter]) {
  var imageLoader: ImageLoader = _imageLoader
  var filters: Seq[Filter[Double]] = _filters
  var converter: ASCIIConverter = _converter
  var exporters: Seq[Exporter] = _exporters
}