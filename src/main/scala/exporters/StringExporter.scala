package exporters
import models.images.ASCIIImage

class StringExporter extends Exporter {
  private var subscribers: Seq[StringExporterSubscriber] = Seq()
  def subscribe(subscriber: StringExporterSubscriber): Unit = {
    subscribers = subscribers :+ subscriber
  }

  override def exportImage(image: ASCIIImage): Unit = {
    val res: String = StringConverter().convert(image)
    subscribers.foreach( sub => sub.notify(res) )
  }
}