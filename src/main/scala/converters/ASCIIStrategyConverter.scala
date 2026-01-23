package converters

import conversionTables.BourkeConversionTable
import models.images.{ASCIIImage, GrayscaleImage, RasterImage}

class ASCIIStrategyConverter(conversionTable: ConversionTable = BourkeConversionTable(), strategy: ConversionStrategy)
extends ASCIIConverter {
  def convert(image: GrayscaleImage): ASCIIImage = {
    image.pixelTransform(strategy.convert(conversionTable))
  }
}