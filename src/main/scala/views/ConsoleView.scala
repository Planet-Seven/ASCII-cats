package views

import generator.{ASCIIArtGenerator, GeneratorArguments}
import views.parser.ArgumentParser_

class ConsoleView(app: ASCIIArtGenerator, args: Seq[String]) extends View{
  def render(): Unit = {
    val argumentParser = ArgumentParser_()

    var parsedArguments: Option[GeneratorArguments] = None

    try parsedArguments = Option(argumentParser.parse(args))
    catch
      case parseError: RuntimeException =>
        println("Invalid use of arguments:")
        println(parseError.getMessage)
        printUsage()

    parsedArguments match {
      case Some(parsed) => app.execute(parsed)
      case None => }
  }

  private def printUsage(): Unit = {
    println(
      "Usage:\n" +
        "  --image <path> | --image-random\n" +
        "  [--table (linearBourke | quadraticBourke | longLinearBourke)]\n" +
        "  [--custom-table <chars>]\n" +
        "  [--invert]\n" +
        "  [--flip x|y]\n" +
        "  [--brightness <value>]\n" +
        "  [--output-console]\n" +
        "  [--output-file <path>]\n")
  }
}