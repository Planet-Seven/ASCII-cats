package filters

import filters.BrightnessFilter
import models.images.GrayscaleImage
import models.pixels.GrayscalePixel
import org.scalatest.funsuite.AnyFunSuite

class BrightnessFilterTest extends AnyFunSuite{
  // Success
 test("Adding positive brightness") {
   //Arrange
   var image: GrayscaleImage = GrayscaleImage(width = 2, height = 1)
   val filter: BrightnessFilter = BrightnessFilter(brightnessAddend = 0.3)
   image.setPixel(0, 0, GrayscalePixel(0.5))
   image.setPixel(1, 0, GrayscalePixel(0.2))

   //Act
   image = filter(image)

   //Assert
   assert(image.getPixel(0, 0).value == 0.8 && image.getPixel(1, 0).value == 0.5)
 }
  test("Adding negative brightness") {
    //Arrange
    var image: GrayscaleImage = GrayscaleImage(width = 2, height = 1)
    val filter: BrightnessFilter = BrightnessFilter(brightnessAddend = -0.3)
    image.setPixel(0, 0, GrayscalePixel(0.5))
    image.setPixel(1, 0, GrayscalePixel(0.8))

    //Act
    image = filter(image)

    //Assert
    assert(image.getPixel(0, 0).value == 0.2 && image.getPixel(1, 0).value == 0.5)
  }
  test("Adding positive brightness with clipping") {
    //Arrange
    var image: GrayscaleImage = GrayscaleImage(width = 2, height = 1)
    val filter: BrightnessFilter = BrightnessFilter(brightnessAddend = 0.9)
    image.setPixel(0, 0, GrayscalePixel(0.5))
    image.setPixel(1, 0, GrayscalePixel(0.2))

    //Act
    image = filter(image)

    //Assert
    assert(image.getPixel(0, 0).value == 1.0 && image.getPixel(1, 0).value == 1.0)
  }
  test("Adding negative brightness with clipping") {
    //Arrange
    var image: GrayscaleImage = GrayscaleImage(width = 2, height = 1)
    val filter: BrightnessFilter = BrightnessFilter(brightnessAddend = -0.9)
    image.setPixel(0, 0, GrayscalePixel(0.5))
    image.setPixel(1, 0, GrayscalePixel(0.2))

    //Act
    image = filter(image)

    //Assert
    assert(image.getPixel(0, 0).value == 0.0 && image.getPixel(1, 0).value == 0.0)
  }
}
