package filters

import models.images.{ArrayImage, GrayscaleImage, RasterImage}

import scala.reflect.ClassTag

class FlipFilter(flipX: Boolean) extends Filter{
  override def apply(image: GrayscaleImage): GrayscaleImage = {
    val transformedImage: GrayscaleImage = GrayscaleImage(image.size._1, image.size._2)
    for (x <- 0 until image.size._1)
      for (y <- 0 until image.size._2) {
        if (flipX) transformedImage.setPixel (image.size._1 - x - 1, y, image.getPixel(x, y))
        else transformedImage.setPixel (x, image.size._2 - y - 1, image.getPixel(x, y))
      }
    transformedImage
  }
}