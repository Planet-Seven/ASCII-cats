package filters

import filters.InvertFilter
import models.images.GrayscaleImage
import models.pixels.GrayscalePixel
import org.scalatest.funsuite.AnyFunSuite

class InvertFilterTest extends AnyFunSuite{
  test("invert"){
    //Arrange
    var image: GrayscaleImage = GrayscaleImage(width = 2, height = 1)
    val filter: InvertFilter = InvertFilter()
    image.setPixel(0, 0, GrayscalePixel(0.25))
    image.setPixel(1, 0, GrayscalePixel(0.0))

    //Act
    image = filter(image)

    //Assert
    assert(image.getPixel(0, 0).value == 0.75 && image.getPixel(1, 0).value == 1.0)
  }
}
