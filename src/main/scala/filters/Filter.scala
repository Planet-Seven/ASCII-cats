package filters

import models.images.RasterImage

trait Filter[T] {
  // applies the filter on an image and returns the filtered image
  def apply(image: RasterImage[T]): RasterImage[T]
}