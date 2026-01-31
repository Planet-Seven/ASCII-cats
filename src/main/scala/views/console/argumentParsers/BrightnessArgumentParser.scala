package views.console.argumentParsers

import filters.BrightnessFilter
import generator.GeneratorArguments

class BrightnessArgumentParser extends NonEmptyArgumentParser {
  usage = "[--brightness <value>]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    val tableArg: Argument = arguments.find((arg: Argument) => arg.name == "--brightness").getOrElse(
      return next.parse(arguments, generatorArguments))

    generatorArguments.filters = generatorArguments.filters :+ BrightnessFilter(tableArg.getParameter.toDouble / 255.0)
    next.parse(arguments.filter((arg: Argument) => arg.name != "--brightness"), generatorArguments)
  }
}