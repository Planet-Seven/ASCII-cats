package models.images

import models.pixels.Pixel

import scala.reflect.ClassTag

trait RasterImage[T](width: Int, height: Int) {
  // returns the size (width, height)
  def size: (Int, Int)
  def getPixel(x: Int, y: Int): T
  def setPixel(x: Int, y: Int, pixel: T): Unit
  // A map function that applies the transform function on each pixel and returns the transformed image
  def pixelTransform( transformFunction: T => T): RasterImage[T]
}