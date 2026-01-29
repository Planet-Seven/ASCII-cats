package views.argumentParsers

import generator.GeneratorArguments
import conversionTables.ArrayConversionTable
import converters.ASCIIConverterImpl
import generator.GeneratorArguments

import java.security.InvalidParameterException

class CustomTableArgumentParser extends NonEmptyArgumentParser {
  usage = "[--custom-table <table>]\n"

  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    val tableArg: Argument = arguments.find((arg: Argument) => arg.name == "--custom-table").getOrElse(
      return next.parse(arguments, generatorArguments))

    generatorArguments.ascii_converter = ASCIIConverterImpl(ArrayConversionTable(tableArg.getParameter.toArray))
    next.parse(arguments.filter((arg: Argument) => arg.name != "--table"), generatorArguments)
  }
}
