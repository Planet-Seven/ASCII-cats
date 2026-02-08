package views.console.argumentParsers

import filters.BrightnessFilter
import generator.GeneratorArguments

class BrightnessArgumentParser extends NonEmptyArgumentParser {
  usage = "[--brightness <value>]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
      arguments.find((arg: Argument) => arg.name == "--brightness") match
      case None => next.parse(arguments, generatorArguments)
      case Some(argument) =>
        generatorArguments.filters = generatorArguments.filters :+ BrightnessFilter(argument.getParameter.toDouble / 255.0)
        next.parse(arguments.filterNot(_.name == "--brightness"), generatorArguments)
  }
}