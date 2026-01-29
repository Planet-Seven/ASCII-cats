package views.argumentParsers

class Argument (val name: String){
  private var parameter: String = ""
  def setParameter(_parameter: String): Unit = { parameter = _parameter }
  def getParameter: String = parameter
}