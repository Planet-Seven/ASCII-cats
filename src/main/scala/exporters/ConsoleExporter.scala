package exporters

import models.images.{ASCIIImage, RasterImage}

class ConsoleExporter extends Exporter {
  override def exportImage(image: ASCIIImage): Unit = {
    Console.print(StringExporter().exportImage(image))
  }
}