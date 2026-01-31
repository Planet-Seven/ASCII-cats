package converters

import conversionTables.ArrayConversionTable
import models.images.{GrayscaleImage, ASCIIImage}
import models.pixels.GrayscalePixel
import org.scalatest.funsuite.AnyFunSuite

class ASCIIConvertorImplTest extends AnyFunSuite{
  test("ASCII Conversion") {
    // Arrange
    val image = GrayscaleImage(width = 2, height = 2)
    image.setPixel(0, 0, GrayscalePixel(0.0))
    image.setPixel(0, 1, GrayscalePixel(0.49))
    image.setPixel(1, 0, GrayscalePixel(0.51))
    image.setPixel(1, 1, GrayscalePixel(1.0))

    val converter = ASCIIConverterImpl(conversionTable = ArrayConversionTable(Array('.','X')))

    // Act
    val asciiImage: ASCIIImage = converter.convert(image)

    // Assert
    assert(asciiImage.getPixel(0,0).value == '.')
    assert(asciiImage.getPixel(0,1).value == '.')
    assert(asciiImage.getPixel(1,0).value == 'X')
    assert(asciiImage.getPixel(1,1).value == 'X')
  }
}
