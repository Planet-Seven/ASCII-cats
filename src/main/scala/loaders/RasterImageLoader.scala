package loaders

import models.images.RGBImage
import models.pixels.RGBPixel
import java.awt.image.BufferedImage
import java.awt.Color
import java.io.File

abstract class RasterImageLoader extends ImageLoader{
  def fetchBufferedImage(): Option[BufferedImage]

  // Poor man's aspect ratio. Makes the resulting ASCII image look okay-ish. Temporary fix.
  private val verticalScaleFactor: Int = 3

  override def loadImage(): RGBImage = {

    var bufferedImage: Option[BufferedImage] = None

    bufferedImage = fetchBufferedImage()
    bufferedImage match
      case None =>
        val image = RGBImage(1, 1)
        image.setPixel(0, 0, RGBPixel(0.0, 0.0, 0.0))
        image

      case Some(bufferedImage) =>
        val width = bufferedImage.getWidth
        val height = bufferedImage.getHeight
        val image = RGBImage(width, height / verticalScaleFactor)

        for (x <- 0 until width)
          for (y <- 0 until height / verticalScaleFactor)
            val color = Color(bufferedImage.getRGB(x, y * verticalScaleFactor))
            image.setPixel(x, y, RGBPixel(color.getRed/255.0, color.getGreen/255.0, color.getBlue/255.0))
        image
  }

}
