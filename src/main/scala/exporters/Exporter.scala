package exporters

import models.images.RasterImage

trait Exporter {
  def exportImage(image: RasterImage[Char]): Any
}
