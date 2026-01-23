package converters

import conversionTables.ArrayConversionTable
import models.images.{ArrayImage, RasterImage}
import org.scalatest.funsuite.AnyFunSuite

class SingleStrategyConvertorTest extends AnyFunSuite{
  test("ASCII Conversion") {
    // Arrange
    val image: RasterImage[Double] = ArrayImage[Double](2, 2)
    image.setValue(0, 0, 0.0)
    image.setValue(0, 1, 0.49)
    image.setValue(1, 0, 0.51)
    image.setValue(1, 1, 1.0)

    val converter: ASCIIStrategyConverter = ASCIIStrategyConverter(conversionTable = ArrayConversionTable(Array('.','X')), strategy = LinearASCIIConversionStrategy())

    // Act
    val asciiImage: RasterImage[Char] = converter.convert(image)

    // Assert
    assert(asciiImage.getValue(0,0) == '.')
    assert(asciiImage.getValue(0,1) == '.')
    assert(asciiImage.getValue(1,0) == 'X')
    assert(asciiImage.getValue(1,1) == 'X')
  }
}
