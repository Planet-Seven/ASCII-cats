package views.console.argumentParsers

import generator.GeneratorArguments
import loaders.CatAPILoader

import java.security.InvalidParameterException

class WidthArgumentParser extends NonEmptyArgumentParser {
  usage = "[--width <value>]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    val tableArg: Argument = arguments.find((arg: Argument) => arg.name == "--width").getOrElse(
      return next.parse(arguments, generatorArguments))

    try generatorArguments.imageLoader = CatAPILoader(width = tableArg.getParameter.toInt)
      catch case _ : NumberFormatException => throw InvalidParameterException("invalid width")

    next.parse(arguments.filter((arg: Argument) => arg.name != "--width"), generatorArguments)
  }
}
