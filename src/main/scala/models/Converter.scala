package models

trait Converter {
  // Used for conversion of the grayscale image to the ascii art image
  def convert(grayscaleImage: RasterImage[Double]): RasterImage[Char]
}