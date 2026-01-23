package models

import images.ArrayImage
import java.awt.Color

trait ImageLoader {
  // loads the image according to the implementation
  def loadImage(): Option[RasterImage[Color]]
}