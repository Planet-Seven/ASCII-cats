package views.console.argumentParsers

import filters.InvertFilter
import generator.GeneratorArguments

class InvertArgumentParser extends NonEmptyArgumentParser {
  usage = "[--invert]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    arguments.find((arg: Argument) => arg.name == "--invert") match
    case None => next.parse(arguments, generatorArguments)
    case Some(_) =>
      generatorArguments.filters = generatorArguments.filters :+ InvertFilter()
      next.parse(arguments.filterNot(_.name == "--invert"), generatorArguments)
  }
}
