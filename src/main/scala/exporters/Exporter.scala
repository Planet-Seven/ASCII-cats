package exporters

import models.images.{ASCIIImage, RasterImage}

trait Exporter {
  def exportImage(image: ASCIIImage): Any
}
