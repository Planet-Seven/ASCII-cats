package views.console.argumentParsers

import generator.GeneratorArguments
import filters.VerticalShrinkFilter

class VerticalShrinkArgumentParser extends NonEmptyArgumentParser {
  usage = "[--shrink <ratio>]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    arguments.find((arg: Argument) => arg.name == "--shrink") match
    case None => next.parse(arguments, generatorArguments)
    case Some(argument) =>
      generatorArguments.filters = generatorArguments.filters :+ VerticalShrinkFilter(argument.getParameter.toDouble)
      next.parse(arguments.filterNot(_.name == "--shrink"), generatorArguments)
  }
}