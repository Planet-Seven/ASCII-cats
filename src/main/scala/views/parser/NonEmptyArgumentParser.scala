package views.parser

abstract class NonEmptyArgumentParser extends ArgumentParser {
  protected var next: ArgumentParser = EmptyArgumentParser()
  def setNext(nextParser: NonEmptyArgumentParser): Unit = {next = nextParser}
}