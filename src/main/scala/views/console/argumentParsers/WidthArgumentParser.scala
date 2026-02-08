package views.console.argumentParsers

import generator.GeneratorArguments
import loaders.CatAPILoader

import java.security.InvalidParameterException

class WidthArgumentParser extends NonEmptyArgumentParser {
  usage = "[--width <value>]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    arguments.find((arg: Argument) => arg.name == "--width") match
    case None => next.parse(arguments, generatorArguments)
    case Some(argument) =>
      try generatorArguments.imageLoader = CatAPILoader(width = argument.getParameter.toInt)
        catch case _ : NumberFormatException => throw InvalidParameterException("invalid width")
      next.parse(arguments.filterNot(_.name == "--width"), generatorArguments)
  }
}