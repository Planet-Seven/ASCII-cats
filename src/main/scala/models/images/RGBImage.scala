package models.images
import models.pixels.RGBPixel

class RGBImage(width: Int, height: Int) extends RasterImage[RGBPixel](width, height){
  private var image: RasterImage[RGBPixel] = ArrayImage[RGBPixel](width, height)

  override def size: (Int, Int) = image.size

  override def getPixel(x: Int, y: Int): RGBPixel = image.getPixel(x, y)

  override def setPixel(x: Int, y: Int, pixel: RGBPixel): Unit = image.setPixel(x, y, pixel)

  override def pixelTransform(transformFunction: RGBPixel => RGBPixel): RGBImage =
    val transformed_image = RGBImage(width, height)
    transformed_image.image = image.pixelTransform(transformFunction)
    transformed_image
}