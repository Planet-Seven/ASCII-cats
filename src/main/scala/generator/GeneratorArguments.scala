package generator

import models.{Converter, Exporter, Filter, ImageLoader}

class GeneratorArguments(
    _imageLoader : ImageLoader,
    _filters : Seq[Filter[Double]],
    _converter : Converter,
    _exporters : Seq[Exporter]) {
  var imageLoader: ImageLoader = _imageLoader
  var filters: Seq[Filter[Double]] = _filters
  var converter: Converter = _converter
  var exporters: Seq[Exporter] = _exporters
}