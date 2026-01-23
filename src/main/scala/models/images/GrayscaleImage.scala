package models.images
import models.pixels.GrayscalePixel

class GrayscaleImage(width: Int, height: Int) extends RasterImage(width, height){
  private var image: RasterImage[GrayscalePixel] = ArrayImage[GrayscalePixel](width, height)

  override def size: (Int, Int) = image.size

  override def getPixel(x: Int, y: Int): GrayscalePixel = image.getPixel(x, y)

  override def setPixel(x: Int, y: Int, pixel: GrayscalePixel): Unit = image.setPixel(x, y, pixel)

  override def pixelTransform(transformFunction: GrayscalePixel => GrayscalePixel): GrayscaleImage =
    val transformed_image = GrayscaleImage(width, height)
    transformed_image.image = image.pixelTransform(transformFunction)
    transformed_image
}