package views.console.argumentParsers

abstract class NonEmptyArgumentParser extends ArgumentParser {
  protected var next: ArgumentParser = EmptyArgumentParser()
  protected var usage: String = ""
  def setNext(nextParser: NonEmptyArgumentParser): NonEmptyArgumentParser = {
    next = nextParser
    nextParser}

  override def printUsage(prevUsage: String): String = {
    next.printUsage(prevUsage + usage) }
}