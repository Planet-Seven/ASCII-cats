package exporters

import models.images.ArrayImage
import org.scalatest.funsuite.AnyFunSuite

class StringExporterTest extends AnyFunSuite {
  test("export full array Image") {
    val image = ArrayImage[Char](2, 2)
    val exporter = StringExporter()

    image.setValue(0, 0, 'a')
    image.setValue(0, 1, 'b')
    image.setValue(1, 0, 'c')
    image.setValue(1, 1, 'd')

    assert(exporter.exportImage(image) == "ac\nbd\n")
  }
  test("export partial array Image") {
    val image = ArrayImage[Char](2, 2)
    val exporter = StringExporter()

    image.setValue(0, 0, 'a')
    image.setValue(1, 1, 'd')

    assert(exporter.exportImage(image) == "a \n d\n")
  }
}
