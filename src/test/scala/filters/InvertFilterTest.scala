package filters

import images.ArrayImage
import models.RasterImage
import filters.InvertFilter
import org.scalatest.funsuite.AnyFunSuite

class InvertFilterTest extends AnyFunSuite{
  test("invert"){
    //Arrange
    var image: RasterImage[Double] = ArrayImage[Double](width = 2, height = 1)
    val filter: InvertFilter = InvertFilter()
    image.setValue(0, 0, 0.25)
    image.setValue(1, 0, 0.0)

    //Act
    image = filter(image)

    //Assert
    assert(image.getValue(0, 0) == 0.75 && image.getValue(1, 0) == 1.0)
  }
}
