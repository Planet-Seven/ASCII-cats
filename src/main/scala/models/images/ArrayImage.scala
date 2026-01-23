package models.images

import models.images.RasterImage
import models.pixels.Pixel

import scala.reflect.ClassTag

class ArrayImage[T: ClassTag](width: Int, height: Int) extends RasterImage[T](width: Int, height: Int){
  private val pixels: Array[Array[T]] = Array.ofDim[T](width, height)

  override def size: (Int, Int) = (width, height)

  private def checkBounds(x: Int, y: Int): Unit = {
    if (x > width || y > height || x < 0 || y < 0)
      throw RuntimeException("out of bounds")
  }

  override def getPixel(x: Int, y: Int): T = {
    checkBounds(x, y)
    pixels(x)(y);
  }
  override def setPixel(x: Int, y: Int, value: T): Unit = {
    checkBounds(x, y)
    pixels(x)(y) = value;
  }

  override def pixelTransform( transformFunction: T => T): RasterImage[T] = {
    val transformedImage = new ArrayImage[T](width, height)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val newValue: T = transformFunction(getPixel(x, y))
        transformedImage.setPixel(x, y, newValue)
      }}
    transformedImage
  }
}
