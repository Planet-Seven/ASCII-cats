package generator

import models.*
import models.images.{ArrayImage, RasterImage}

import java.awt.Color

class ASCIIArtGenerator {
  def execute(args: GeneratorArguments): Unit = {

    // load
    val rgbImage: RasterImage[Color] = args.imageLoader.loadImage().getOrElse(ArrayImage[Color](100,100))

    //convert to greyscale
    val grayscaleImage: RasterImage[Double] = rgbImage.pixelTransform(
      (pixel: Color) => 0.3 * pixel.getRed/255.0 + 0.59 * pixel.getGreen/255.0 + 0.11 * pixel.getBlue/255.0 )

    //apply filters
    val filteredImage: RasterImage[Double] =
      args.filters.foldLeft(grayscaleImage)( (acc, filter) => filter(acc) )
      
    //convert to ASCII
    val asciiImage: RasterImage[Char]= args.converter.convert(filteredImage)

    //export
    args.exporters.foreach((exporter) => exporter.exportImage(asciiImage) )
  }
}
