package views.argumentParsers

import exporters.FileExporter
import generator.GeneratorArguments

import java.security.InvalidParameterException

class OutputFileArgumentParser extends NonEmptyArgumentParser {
  usage = "[--output-console]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    val tableArg: Argument = arguments.find((arg: Argument) => arg.name == "--output-file").getOrElse(
      return next.parse(arguments, generatorArguments))

    try generatorArguments.exporters = generatorArguments.exporters :+ FileExporter(tableArg.getParameter)
      catch case _: IllegalArgumentException => throw InvalidParameterException("invalid path")

    next.parse(arguments.filter((arg: Argument) => arg.name != "--output-file"), generatorArguments)
  }
}
