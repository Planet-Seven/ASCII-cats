package converters

import models.images.{ASCIIImage, GrayscaleImage, RasterImage}

trait ASCIIConverter {
  // Used for conversion of the grayscale image to the ascii art image
  def convert(image: GrayscaleImage): ASCIIImage
}