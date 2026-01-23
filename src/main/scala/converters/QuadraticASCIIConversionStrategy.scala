package converters

import Math.pow

class QuadraticConversionStrategy extends ConversionStrategy {
  override def convert(conversionTable: ConversionTable)(value: Double): Char = {
    if (value > 1 || value < 0)
      throw RuntimeException("Out of bounds")

    conversionTable.getChar(((conversionTable.length - 1) * pow(value, 2)).round.toInt)
  }
}