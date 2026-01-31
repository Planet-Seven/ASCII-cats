package loaders

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.IOException
import java.net.URI

class CatAPILoader(width: Int = 120) extends RasterImageLoader {
  override def fetchBufferedImage(): Option[BufferedImage] =
    try Option(ImageIO.read(new URI("https://cataas.com/cat?width=" + width.toString).toURL))
    catch case e: IOException => None
}