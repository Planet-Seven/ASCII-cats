package loaders

import images.ArrayImage
import models.{ImageLoader, RasterImage}

import java.awt.image.BufferedImage
import java.awt.Color
import java.io.File

abstract class RasterImageLoader extends ImageLoader{
  def fetchBufferedImage(): Option[BufferedImage]

  // Poor man's aspect ratio. Makes the resulting ASCII image look okay-ish. Temporary fix.
  private val verticalScaleFactor: Int = 3

  override def loadImage(): Option[RasterImage[Color]] = {

    var bufferedImage: Option[BufferedImage] = None

    bufferedImage = fetchBufferedImage()
    bufferedImage match
      case None =>
        println("Failed to fetch the image")
        None

      case Some(bufferedImage) =>
        val width = bufferedImage.getWidth
        val height = bufferedImage.getHeight
        val arrayImage: RasterImage[Color] = ArrayImage[Color](width, height / verticalScaleFactor)

        for (x <- 0 until width)
          for (y <- 0 until height / verticalScaleFactor)
            arrayImage.setValue(x, y, Color(bufferedImage.getRGB(x, y * verticalScaleFactor)))

        Option(arrayImage)
  }

}
