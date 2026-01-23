package filters

import models.{Filter, RasterImage}
import images.ArrayImage

import scala.reflect.ClassTag

class FlipFilter(flipX: Boolean) extends Filter[Double]{
  override def apply(image: RasterImage[Double]): RasterImage[Double] = {
    val transformedImage: RasterImage[Double] = ArrayImage[Double](image.size._1, image.size._2)
    for (x <- 0 until image.size._1)
      for (y <- 0 until image.size._2) {
        if (flipX) transformedImage.setValue (image.size._1 - x - 1, y, image.getValue(x, y))
        else transformedImage.setValue (x, image.size._2 - y - 1, image.getValue(x, y))
      }

    transformedImage
  }
}