package exporters

import models.{Exporter, RasterImage}
import utils.FilePath

import java.io.{File, IOException, PrintWriter}

class FileExporter(_path: String) extends Exporter {
  private val path: FilePath = FilePath(_path)

  override def exportImage(image: RasterImage[Char]): Unit = {
    try {
      val writer = PrintWriter(File(path.getPath))
      writer.write(StringExporter().exportImage(image))
      writer.close()
    }
    catch {
      case e: IOException => println("Failed to save to the file")
    }
  }
}
