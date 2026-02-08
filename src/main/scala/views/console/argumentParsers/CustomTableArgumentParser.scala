package views.console.argumentParsers

import generator.GeneratorArguments
import conversionTables.ArrayConversionTable
import converters.ASCIIConverterImpl
import generator.GeneratorArguments

import java.security.InvalidParameterException

class CustomTableArgumentParser extends NonEmptyArgumentParser {
  usage = "[--custom-table <table>]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    arguments.find((arg: Argument) => arg.name == "--custom-table") match
    case None => next.parse(arguments, generatorArguments)
    case Some(argument) =>
      generatorArguments.ascii_converter = ASCIIConverterImpl(ArrayConversionTable(argument.getParameter.toArray))
      next.parse(arguments.filterNot(_.name == "--table"), generatorArguments)
  }
}