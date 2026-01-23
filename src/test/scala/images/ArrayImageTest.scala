package images

import org.scalatest.funsuite.AnyFunSuite
import models.images.{ArrayImage, RasterImage}

class ArrayImageTest extends AnyFunSuite{
  test("getPixel, setPixel, pixelTransform") {
    // Arrange
    val dim: Int = 100
    var image: RasterImage[Int] = ArrayImage[Int](dim, dim)

    // Act
    for (x <- 0 until dim)
      for (y <- 0 until dim)
        image.setValue(x, y, x + y)

    image = image.pixelTransform(pixel => pixel + 1)

    // Assert
    for (x <- 0 until dim)
      for (y <- 0 until dim)
        assert(image.getValue(x, y) == x + y + 1)
  }
}
