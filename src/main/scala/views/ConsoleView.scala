package views

import generator.{ASCIIArtGenerator, GeneratorArguments}

import java.security.InvalidParameterException

class ConsoleView(app: ASCIIArtGenerator, args: Seq[String]) extends View{
  def render(): Unit = {
    val argumentParser = ConsoleArgumentParser()

    var parsedArguments: Option[GeneratorArguments] = None

    try parsedArguments = Option(argumentParser.parse(args))
    catch
      case parseError: InvalidParameterException =>
        println(parseError.getMessage)
        println(argumentParser.printUsage())

    parsedArguments match {
      case Some(parsed) => app.execute(parsed)
      case None => }
  }
}