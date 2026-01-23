package exporters

import models.images.RasterImage

class StringExporter extends Exporter {
  override def exportImage(image: RasterImage[Char]): String = {
    var res: String = ""
    for (y <- 0 until image.size._2)
      for (x <- 0 until image.size._1) {
        val value: Char = image.getValue(x, y)
        value match
          case '\u0000' => res = res.appended(' ')
          case other => res = res.appended(other)
      }
      res = res.appended('\n')
    res
  }
}
