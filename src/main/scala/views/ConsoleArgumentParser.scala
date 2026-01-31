package views

import converters.ASCIIConverterImpl
import generator.GeneratorArguments
import loaders.CatAPILoader
import views.argumentParsers.Argument
import argumentParsers.*

import java.security.InvalidParameterException

class ConsoleArgumentParser {
  private val parsers: NonEmptyArgumentParser = CustomTableArgumentParser()
    parsers
      .setNext(WidthArgumentParser())
      .setNext(InvertArgumentParser())
      .setNext(FlipArgumentParser())
      .setNext(BrightnessArgumentParser())
      .setNext(OutputConsoleArgumentParser())
      .setNext(OutputFileArgumentParser())

  def printUsage(): String = {
    "Usage:\n" + parsers.printUsage("")
  }

  def parse(_args: Seq[String]): GeneratorArguments = {
    var pairedArgs: List[Argument] = List()
    var args = _args

    while (args.nonEmpty) {
      val top: String = args.head
      if (top.startsWith("--")) {
        val argument: Argument = Argument(top)
        args = args.tail
        if (args.nonEmpty && !args.head.startsWith("--"))
          argument.setParameter(args.head)
          args = args.tail
        pairedArgs = pairedArgs :+ argument
      }
      else throw InvalidParameterException("Unknown token: " + top + "\n")
    }

    val generatorArguments = GeneratorArguments(
      _imageLoader = CatAPILoader(),
      _filters = Seq(),
      _ascii_converter = ASCIIConverterImpl(),
      _exporters = Seq()
    )

    parsers.parse(pairedArgs, generatorArguments)
  }
}
