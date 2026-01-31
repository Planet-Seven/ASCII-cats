package views.console.argumentParsers

import generator.GeneratorArguments
import filters.VerticalShrinkFilter

class VerticalShrinkArgumentParser extends NonEmptyArgumentParser {
  usage = "[--shrink <ratio>]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    val tableArg: Argument = arguments.find((arg: Argument) => arg.name == "--shrink").getOrElse(
      return next.parse(arguments, generatorArguments))

    generatorArguments.filters = generatorArguments.filters :+ VerticalShrinkFilter(tableArg.getParameter.toDouble)
    next.parse(arguments.filter((arg: Argument) => arg.name != "--shrink"), generatorArguments)
  }
}
