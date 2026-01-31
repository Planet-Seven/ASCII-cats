package filters

import filters.FlipFilter
import models.images.GrayscaleImage
import models.pixels.GrayscalePixel
import org.scalatest.funsuite.AnyFunSuite

class FlipFilterTest extends AnyFunSuite{
  test("flip vertical"){
    //Arrange
    var image: GrayscaleImage = GrayscaleImage(width = 2, height = 2)
    val filter: FlipFilter = FlipFilter(flipX = false)
    image.setPixel(0, 0, GrayscalePixel(0.1))
    image.setPixel(0, 1, GrayscalePixel(0.2))
    image.setPixel(1, 0, GrayscalePixel(0.3))
    image.setPixel(1, 1, GrayscalePixel(0.4))

    //Act
    image = filter(image)

    //Assert
    assert(
      image.getPixel(0, 0).value == 0.2 &&
      image.getPixel(0, 1).value == 0.1 &&
      image.getPixel(1, 0).value == 0.4 &&
      image.getPixel(1, 1).value == 0.3)
  }
  test("flip horizontal") {
      //Arrange
      var image: GrayscaleImage = GrayscaleImage(width = 2, height = 2)
      val filter: FlipFilter = FlipFilter(flipX = true)
      image.setPixel(0, 0, GrayscalePixel(0.1))
      image.setPixel(0, 1, GrayscalePixel(0.2))
      image.setPixel(1, 0, GrayscalePixel(0.3))
      image.setPixel(1, 1, GrayscalePixel(0.4))

      //Act
      image = filter(image)

      //Assert
      assert(
        image.getPixel(0, 0).value == 0.3 &&
        image.getPixel(0, 1).value == 0.4 &&
        image.getPixel(1, 0).value == 0.1 &&
        image.getPixel(1, 1).value == 0.2)
  }
}
