package converters

import conversionTables.BourkeConversionTable
import models.{ConversionStrategy, ConversionTable, Converter, RasterImage}

class SingleStrategyConverter(conversionTable: ConversionTable = BourkeConversionTable(), strategy: ConversionStrategy)
extends Converter {
  def convert(image: RasterImage[Double]): RasterImage[Char] = {
    image.pixelTransform(strategy.convert(conversionTable))
  }
}