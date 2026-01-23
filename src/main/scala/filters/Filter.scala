package filters

import models.images.{GrayscaleImage, RasterImage}

trait Filter {
  // applies the filter on an image and returns the filtered image
  def apply(image: GrayscaleImage): GrayscaleImage
}