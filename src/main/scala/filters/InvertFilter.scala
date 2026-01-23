package filters

import models.{Filter, RasterImage}

class InvertFilter extends Filter[Double]{
  override def apply(image: RasterImage[Double]): RasterImage[Double] = {
    image.pixelTransform( ( pixelValue: Double ) => 1 - pixelValue )
  }
}