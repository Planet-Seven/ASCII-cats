package models.images
import models.pixels.ASCIIPixel

class ASCIIImage(width: Int, height: Int) extends RasterImage(width, height){
  private var image: RasterImage[ASCIIPixel] = ArrayImage[ASCIIPixel](width, height)

  override def size: (Int, Int) = image.size

  override def getPixel(x: Int, y: Int): ASCIIPixel = image.getPixel(x, y)

  override def setPixel(x: Int, y: Int, pixel: ASCIIPixel): Unit = image.setPixel(x, y, pixel)

  override def pixelTransform(transformFunction: ASCIIPixel => ASCIIPixel): ASCIIImage =
    val transformed_image = ASCIIImage(width, height)
    transformed_image.image = image.pixelTransform(transformFunction)
    transformed_image
}