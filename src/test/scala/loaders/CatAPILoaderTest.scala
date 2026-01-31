package loaders

import models.images.RGBImage
import org.scalatest.funsuite.AnyFunSuite

import java.awt.Color

class CatAPILoaderTest extends AnyFunSuite {
  test("Connection to API successful") {
    val loader = CatAPILoader()
    val catImage: RGBImage = loader.loadImage()
    assert(catImage.size._1 == 120)
  }
}
