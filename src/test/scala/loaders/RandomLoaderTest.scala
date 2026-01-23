package loaders

import models.images.RasterImage
import org.scalatest.funsuite.AnyFunSuite

import java.awt.Color

class RandomLoaderTest extends AnyFunSuite {
  test("Connection to API successful") {
    val loader = CatAPILoader()
    val catImage: Option[RasterImage[Color]] = loader.loadImage()
    catImage match
      case Some(img) => assert(img.size._1 == 120)
      case None => assert(false)
  }
}
