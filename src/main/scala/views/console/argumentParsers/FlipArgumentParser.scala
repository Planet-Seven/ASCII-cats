package views.console.argumentParsers

import generator.GeneratorArguments
import filters.FlipFilter

import java.security.InvalidParameterException

class FlipArgumentParser extends NonEmptyArgumentParser {
  usage = "[--flip]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    arguments.find((arg: Argument) => arg.name == "--flip") match
    case None => next.parse(arguments, generatorArguments)
    case Some(argument) =>
      argument.getParameter match
        case "x" => generatorArguments.filters = generatorArguments.filters :+ FlipFilter(flipX = true)
        case "y" => generatorArguments.filters = generatorArguments.filters :+ FlipFilter(flipX = false)
        case value => throw InvalidParameterException(s"Invalid flip axis: $value.")
      next.parse(arguments.filterNot(_.name == "--flip"), generatorArguments)
  }
}