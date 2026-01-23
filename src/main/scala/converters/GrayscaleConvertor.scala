package converters

import models.images.{GrayscaleImage, RGBImage}
import models.pixels.{GrayscalePixel, RGBPixel}

import java.awt.Color

class GrayscaleConvertor {
  def convert(image: RGBImage): GrayscaleImage = {
    val converted_image = GrayscaleImage(image.size._1, image.size._2)
    for (x <- 0 until image.size._1)
      for (y <- 0 until image.size._2) {
        val rgb_pixel = image.getPixel(x, y)
        val grayscale_pixel = GrayscalePixel(0.3 * rgb_pixel.r/255.0 + 0.59 * rgb_pixel.g/255.0 + 0.11 * rgb_pixel.b/255.0 )
        converted_image.setPixel(x, y, grayscale_pixel)
      }
    converted_image
  }
}
