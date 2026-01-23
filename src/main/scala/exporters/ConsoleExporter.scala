package exporters

import models.images.RasterImage

class ConsoleExporter extends Exporter {
  override def exportImage(image: RasterImage[Char]): Unit = {
    Console.print(StringExporter().exportImage(image))
  }
}