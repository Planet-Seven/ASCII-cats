package filters

import models.images.{GrayscaleImage, RasterImage}
import utils.MathUtils

class BrightnessFilter(brightnessAddend: Double) extends Filter[Double]{
  override def apply(image: GrayscaleImage): GrayscaleImage = {
    image.pixelTransform( pixelValue => MathUtils.clamp(pixelValue + brightnessAddend, 0, 1) )
  }
}