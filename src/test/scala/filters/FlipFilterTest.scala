package filters

import images.ArrayImage
import models.RasterImage
import filters.FlipFilter
import org.scalatest.funsuite.AnyFunSuite

class FlipFilterTest extends AnyFunSuite{
  test("flip vertical"){
    //Arrange
    var image: RasterImage[Double] = ArrayImage[Double](width = 2, height = 2)
    val filter: FlipFilter = FlipFilter(flipX = false)
    image.setValue(0, 0, 0.1)
    image.setValue(0, 1, 0.2)
    image.setValue(1, 0, 0.3)
    image.setValue(1, 1, 0.4)

    //Act
    image = filter(image)

    //Assert
    assert(
      image.getValue(0, 0) == 0.2 &&
      image.getValue(0, 1) == 0.1 &&
      image.getValue(1, 0) == 0.4 &&
      image.getValue(1, 1) == 0.3)
  }
  test("flip horizontal") {
      //Arrange
      var image: RasterImage[Double] = ArrayImage[Double](width = 2, height = 2)
      val filter: FlipFilter = FlipFilter(flipX = true)
      image.setValue(0, 0, 0.1)
      image.setValue(0, 1, 0.2)
      image.setValue(1, 0, 0.3)
      image.setValue(1, 1, 0.4)

      //Act
      image = filter(image)

      //Assert
      assert(
        image.getValue(0, 0) == 0.3 &&
        image.getValue(0, 1) == 0.4 &&
        image.getValue(1, 0) == 0.1 &&
        image.getValue(1, 1) == 0.2)
  }
}
