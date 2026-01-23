package loaders

import models.images.{ArrayImage, RasterImage}

import java.awt.Color

trait ImageLoader {
  // loads the image according to the implementation
  def loadImage(): Option[RasterImage[Color]]
}