package models

import scala.reflect.ClassTag

trait RasterImage[T](width: Int, height: Int) {
  // returns the size (width, height)
  def size: (Int, Int)
  def getValue(x: Int, y: Int): T
  def setValue(x: Int, y: Int, value: T): Unit
  // A map function that applies the transform function on each pixel and returns the transformed image
  def pixelTransform[S: ClassTag]( transformFunction: T => S): RasterImage[S]
}