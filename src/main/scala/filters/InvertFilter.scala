package filters

import models.images.RasterImage

class InvertFilter extends Filter[Double]{
  override def apply(image: RasterImage[Double]): RasterImage[Double] = {
    image.pixelTransform( ( pixelValue: Double ) => 1 - pixelValue )
  }
}