package views.argumentParsers

import generator.GeneratorArguments
import exporters.ConsoleExporter

class OutputConsoleArgumentParser extends NonEmptyArgumentParser {
  usage = "[--output-console]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    val tableArg: Argument = arguments.find((arg: Argument) => arg.name == "--output-console").getOrElse(
      return next.parse(arguments, generatorArguments))

    generatorArguments.exporters = generatorArguments.exporters :+ ConsoleExporter()
    next.parse(arguments.filter((arg: Argument) => arg.name != "--output-console"), generatorArguments)
  }
}
