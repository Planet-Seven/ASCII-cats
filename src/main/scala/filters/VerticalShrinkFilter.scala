package filters
import models.images.GrayscaleImage

class VerticalShrinkFilter(shrinkRatio: Double) extends Filter {
  require(shrinkRatio > 1)
  override def apply(image: GrayscaleImage): GrayscaleImage = {

    val newHeight = (image.size._2 / shrinkRatio).toInt
    val rowsToDrop = image.size._2 - newHeight
    val dropIndex = image.size._2 / rowsToDrop

    val transformedImage = GrayscaleImage(image.size._1, newHeight)

    for (x <- 0 until image.size._1)
      var resized_y = 0
      for (y <- 0 until image.size._2) {
        if (y % dropIndex != 0) {
          transformedImage.setPixel(x, resized_y, image.getPixel(x, y))
          resized_y += 1
        }
      }
    transformedImage
  }
}
