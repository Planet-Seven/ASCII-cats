package exporters

class StringExporterSubscriber {
  var res: String = ""
  def notify(_res: String): Unit = res = _res
}
