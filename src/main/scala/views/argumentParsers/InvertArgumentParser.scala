package views.argumentParsers

import filters.InvertFilter
import generator.GeneratorArguments

class InvertArgumentParser extends NonEmptyArgumentParser {
  usage = "[--invert]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    val tableArg: Argument = arguments.find((arg: Argument) => arg.name == "--invert").getOrElse(
      return next.parse(arguments, generatorArguments))

    generatorArguments.filters = generatorArguments.filters :+ InvertFilter()
    next.parse(arguments.filter((arg: Argument) => arg.name != "--invert"), generatorArguments)
  }
}
