package images

import models.RasterImage

import scala.reflect.ClassTag

class ArrayImage[T: ClassTag](width: Int, height: Int) extends RasterImage[T](width: Int, height: Int){
  private val pixels: Array[Array[T]] = Array.ofDim[T](width, height)

  override def size: (Int, Int) = (width, height)

  private def checkBounds(x: Int, y: Int): Unit = {
    if (x > width || y > height || x < 0 || y < 0)
      throw RuntimeException("out of bounds")
  }
  
  override def getValue(x: Int, y: Int): T = {
    checkBounds(x, y)
    pixels(x)(y);
  }
  override def setValue(x: Int, y: Int, value: T): Unit = {
    checkBounds(x, y)
    pixels(x)(y) = value;
  }

  override def pixelTransform[S: ClassTag]( transformFunction: T => S): RasterImage[S] = {
    val transformedImage = new ArrayImage[S](width, height)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val newValue: S = transformFunction(getValue(x, y))
        transformedImage.setValue(x, y, newValue)
    }}
    transformedImage
  }
}
