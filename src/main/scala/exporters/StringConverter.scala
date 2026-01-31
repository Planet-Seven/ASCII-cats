package exporters

import models.images.{ASCIIImage, RasterImage}

class StringConverter{
  def convert(image: ASCIIImage): String = {
    var res: String = ""
    for (y <- 0 until image.size._2){
      for (x <- 0 until image.size._1) {
        if (image.getPixel(x,y) != null) {
          res = res.appended(image.getPixel(x, y).value)
        } else {
          res = res.appended(' ')}}
      res = res.appended('\n')}
    res
  }
}
