package filters

import models.{Filter, RasterImage}
import utils.MathUtils

class BrightnessFilter(brightnessAddend: Double) extends Filter[Double]{
  override def apply(image: RasterImage[Double]): RasterImage[Double] = {
    image.pixelTransform( pixelValue => MathUtils.clamp(pixelValue + brightnessAddend, 0, 1) )
  }
}