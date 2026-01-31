package views

import generator.{ASCIIArtGenerator, GeneratorArguments}

import java.security.InvalidParameterException

class ConsoleView(app: ASCIIArtGenerator, args: Seq[String]) extends View{
  def render(): Unit = {
    val argumentParser = ConsoleArgumentParser()
    try {
      val parsedArguments: GeneratorArguments = argumentParser.parse(args)
      app.execute(parsedArguments)
    } catch
    case parseError: InvalidParameterException =>
      println(parseError.getMessage)
      println(argumentParser.printUsage())
  }
}