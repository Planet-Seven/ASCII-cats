package filters

import models.images.GrayscaleImage
import models.pixels.GrayscalePixel

class InvertFilter extends Filter{
  override def apply(image: GrayscaleImage): GrayscaleImage = {
    image.pixelTransform( pixel => GrayscalePixel(1 - pixel.value))
  }
}