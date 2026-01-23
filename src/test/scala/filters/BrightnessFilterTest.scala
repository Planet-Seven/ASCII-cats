package filters

import images.ArrayImage
import models.RasterImage
import filters.BrightnessFilter
import org.scalatest.funsuite.AnyFunSuite

class BrightnessFilterTest extends AnyFunSuite{
  // Success
 test("Adding positive brightness") {
   //Arrange
   var image: RasterImage[Double] = ArrayImage[Double](width = 2, height = 1)
   val filter: BrightnessFilter = BrightnessFilter(brightnessAddend = 0.3)
   image.setValue(0, 0, 0.5)
   image.setValue(1, 0, 0.2)

   //Act
   image = filter(image)

   //Assert
   assert(image.getValue(0, 0) == 0.8 && image.getValue(1, 0) == 0.5)
 }
  test("Adding negative brightness") {
    //Arrange
    var image: RasterImage[Double] = ArrayImage[Double](width = 2, height = 1)
    val filter: BrightnessFilter = BrightnessFilter(brightnessAddend = -0.3)
    image.setValue(0, 0, 0.5)
    image.setValue(1, 0, 0.8)

    //Act
    image = filter(image)

    //Assert
    assert(image.getValue(0, 0) == 0.2 && image.getValue(1, 0) == 0.5)
  }
  test("Adding positive brightness with clipping") {
    //Arrange
    var image: RasterImage[Double] = ArrayImage[Double](width = 2, height = 1)
    val filter: BrightnessFilter = BrightnessFilter(brightnessAddend = 0.9)
    image.setValue(0, 0, 0.5)
    image.setValue(1, 0, 0.2)

    //Act
    image = filter(image)

    //Assert
    assert(image.getValue(0, 0) == 1.0 && image.getValue(1, 0) == 1.0)
  }
  test("Adding negative brightness with clipping") {
    //Arrange
    var image: RasterImage[Double] = ArrayImage[Double](width = 2, height = 1)
    val filter: BrightnessFilter = BrightnessFilter(brightnessAddend = -0.9)
    image.setValue(0, 0, 0.5)
    image.setValue(1, 0, 0.2)

    //Act
    image = filter(image)

    //Assert
    assert(image.getValue(0, 0) == 0.0 && image.getValue(1, 0) == 0.0)
  }
}
