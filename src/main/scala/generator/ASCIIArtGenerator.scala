package generator

import converters.GrayscaleConvertor
import models.*
import models.images.{ASCIIImage, ArrayImage, GrayscaleImage, RGBImage}

import java.awt.Color

class ASCIIArtGenerator {
  def execute(args: GeneratorArguments): Unit = {

    // load
    val rgbImage: RGBImage = args.imageLoader.loadImage()

    //convert to greyscale
    val grayscaleImage: GrayscaleImage = GrayscaleConvertor().convert(rgbImage)
    //apply filters
    val filteredImage: GrayscaleImage =
      args.filters.foldLeft(grayscaleImage)( (acc, filter) => filter(acc) )

    //convert to ASCII
    val asciiImage: ASCIIImage = args.ascii_converter.convert(filteredImage)

    //export
    args.exporters.foreach((exporter) => exporter.exportImage(asciiImage) )
  }
}
