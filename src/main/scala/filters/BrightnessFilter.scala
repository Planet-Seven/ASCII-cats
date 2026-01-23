package filters

import models.images.{GrayscaleImage, RasterImage}
import models.pixels.GrayscalePixel
import utils.MathUtils

class BrightnessFilter(brightnessAddend: Double) extends Filter{
  override def apply(image: GrayscaleImage): GrayscaleImage = {
    image.pixelTransform( pixel => GrayscalePixel(MathUtils.clamp(pixel.value + brightnessAddend, 0, 1)))
  }
}