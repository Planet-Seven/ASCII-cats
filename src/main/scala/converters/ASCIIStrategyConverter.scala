package converters

import conversionTables.BourkeConversionTable
import models.images.{ASCIIImage, GrayscaleImage}
import models.pixels.ASCIIPixel

class ASCIIStrategyConverter(conversionTable: ConversionTable = BourkeConversionTable(), strategy: ConversionStrategy)
extends ASCIIConverter {
  def convert(image: GrayscaleImage): ASCIIImage = {
    val converted_image = ASCIIImage(image.size._1, image.size._2)
    for (x <- 0 until image.size._1)
      for (y <- 0 until image.size._2) {
        val grayscale_pixel = image.getPixel(x, y)
        val ascii_pixel = ASCIIPixel(strategy.convert(conversionTable)(grayscale_pixel.value))
        converted_image.setPixel(x, y, ascii_pixel)
      }
    converted_image
  }
}