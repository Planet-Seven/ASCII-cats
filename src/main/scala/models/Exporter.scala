package models

trait Exporter {
  def exportImage(image: RasterImage[Char]): Any
}
