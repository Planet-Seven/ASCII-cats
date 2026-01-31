package images

import org.scalatest.funsuite.AnyFunSuite
import models.images.GrayscaleImage
import models.pixels.GrayscalePixel

class GrayscaleImageTest extends AnyFunSuite{
  test("getPixel, setPixel, pixelTransform") {
    // Arrange
    val dim: Int = 100
    var image: GrayscaleImage = GrayscaleImage(width = dim, height = dim)

    // Act
    for (x <- 0 until dim)
      for (y <- 0 until dim)
        image.setPixel(x, y, GrayscalePixel((x + y)/(2.0*dim)))

    image = image.pixelTransform(pixel => GrayscalePixel(pixel.value/2))

    // Assert
    for (x <- 0 until dim)
      for (y <- 0 until dim)
        assert(image.getPixel(x, y).value == (x + y)/(4.0*dim))
  }
}
