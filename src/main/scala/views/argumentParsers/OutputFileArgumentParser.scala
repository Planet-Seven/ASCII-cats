package views.argumentParsers

import exporters.FileExporter
import generator.GeneratorArguments

class OutputFileArgumentParser extends NonEmptyArgumentParser {
  usage = "[--output-console]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    val tableArg: Argument = arguments.find((arg: Argument) => arg.name == "--output-file").getOrElse(
      return next.parse(arguments, generatorArguments))

    generatorArguments.exporters = generatorArguments.exporters :+ FileExporter(tableArg.getParameter)
    next.parse(arguments.filter((arg: Argument) => arg.name != "--output-file"), generatorArguments)
  }
}
