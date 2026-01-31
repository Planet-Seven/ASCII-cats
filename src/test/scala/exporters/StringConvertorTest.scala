package exporters

import models.images.ASCIIImage
import models.pixels.ASCIIPixel
import org.scalatest.funsuite.AnyFunSuite

class StringConvertorTest extends AnyFunSuite {
  test("export full array Image") {
    val image = ASCIIImage(width = 2, height = 2)
    val converter = StringConverter()

    image.setPixel(0, 0, ASCIIPixel('a'))
    image.setPixel(0, 1, ASCIIPixel('b'))
    image.setPixel(1, 0, ASCIIPixel('c'))
    image.setPixel(1, 1, ASCIIPixel('d'))

    assert(converter.convert(image) == "ac\nbd\n")
  }
  test("export partial array Image") {
    val image = ASCIIImage(width = 2, height = 2)
    val converter = StringConverter()

    image.setPixel(0, 0, ASCIIPixel('a'))
    image.setPixel(1, 1, ASCIIPixel('d'))

    assert(converter.convert(image) == "a \n d\n")
  }
}
