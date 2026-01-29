package converters

import conversionTables.BourkeConversionTable
import models.images.{ASCIIImage, GrayscaleImage}
import models.pixels.ASCIIPixel

class ASCIIConverterImpl(conversionTable: ConversionTable = BourkeConversionTable()) extends ASCIIConverter {
  def convert(image: GrayscaleImage): ASCIIImage = {
    val converted_image = ASCIIImage(image.size._1, image.size._2)
    for (x <- 0 until image.size._1)
      for (y <- 0 until image.size._2) {
        print(x, y)
        val grayscale_pixel = image.getPixel(x, y)
        require(grayscale_pixel.value <= 1 || grayscale_pixel.value >= 0)
        val ascii_pixel = ASCIIPixel(
          conversionTable.getChar(((conversionTable.length - 1) * grayscale_pixel.value).round.toInt))
        converted_image.setPixel(x, y, ascii_pixel)
      }
    converted_image
  }
}