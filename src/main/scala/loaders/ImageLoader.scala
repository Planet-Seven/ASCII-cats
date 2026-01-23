package loaders

import models.images.RGBImage

import java.awt.Color

trait ImageLoader {
  // loads the image according to the implementation
  def loadImage(): RGBImage
}