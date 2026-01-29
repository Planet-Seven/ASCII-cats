package views.argumentParsers

import generator.GeneratorArguments
import filters.FlipFilter

class FlipArgumentParser extends NonEmptyArgumentParser {
  usage = "[--flip]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    val tableArg: Argument = arguments.find((arg: Argument) => arg.name == "--flip").getOrElse(
      return next.parse(arguments, generatorArguments))

    tableArg.getParameter match
      case "x" => generatorArguments.filters = generatorArguments.filters :+ FlipFilter(flipX = true)
      case "y" => generatorArguments.filters = generatorArguments.filters :+ FlipFilter(flipX = false)
      case _ => throw RuntimeException("Invalid flip axis.")

    next.parse(arguments.filter((arg: Argument) => arg.name != "--flip"), generatorArguments)
  }
}