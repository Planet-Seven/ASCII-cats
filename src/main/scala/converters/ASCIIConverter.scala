package converters

import models.images.RasterImage

trait ASCIIConverter {
  // Used for conversion of the grayscale image to the ascii art image
  def convert(grayscaleImage: RasterImage[Double]): RasterImage[Char]
}