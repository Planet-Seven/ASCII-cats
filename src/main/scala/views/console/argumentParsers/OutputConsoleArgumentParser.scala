package views.console.argumentParsers

import generator.GeneratorArguments
import exporters.ConsoleExporter

import java.security.InvalidParameterException

class OutputConsoleArgumentParser extends NonEmptyArgumentParser {
  usage = "[--output-console]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    arguments.find((arg: Argument) => arg.name == "--output-console") match
      case None => next.parse(arguments, generatorArguments)
      case Some(_) =>
        generatorArguments.exporters = generatorArguments.exporters :+ ConsoleExporter()
        next.parse(arguments.filterNot(_.name == "--output-console"), generatorArguments)
  }
}
