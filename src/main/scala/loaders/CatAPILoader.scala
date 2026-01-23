package loaders

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.IOException
import java.net.URI

class CatAPILoader extends RasterImageLoader {
  override def fetchBufferedImage(): Option[BufferedImage] =
    try Option(ImageIO.read(new URI("https://cataas.com/cat?width=120").toURL))
    catch case e: IOException => None
}