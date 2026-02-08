package views.console.argumentParsers

import exporters.FileExporter
import generator.GeneratorArguments

import java.security.InvalidParameterException

class OutputFileArgumentParser extends NonEmptyArgumentParser {
  usage = "[--output-console]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    arguments.find((arg: Argument) => arg.name == "--output-file") match
    case None => next.parse(arguments, generatorArguments)
    case Some(argument) =>
      try generatorArguments.exporters = generatorArguments.exporters :+ FileExporter(argument.getParameter)
        catch case _: IllegalArgumentException => throw InvalidParameterException("invalid path")
      next.parse(arguments.filterNot(_.name == "--output-file"), generatorArguments)
  }
}