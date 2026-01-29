package views.parser
import conversionTables.LongBourkeConversionTable
import converters.{ASCIIStrategyConverter, LinearASCIIConversionStrategy, QuadraticASCIIConversionStrategy}
import generator.GeneratorArguments

import java.security.InvalidParameterException

class TableArgumentParser extends NonEmptyArgumentParser {
  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    val tableArg: Argument = arguments.find((arg: Argument) => arg.name == "--table").getOrElse(
      return next.parse(arguments, generatorArguments))

    tableArg.getParameter match
      case "linearBourke" => generatorArguments.ascii_converter = ASCIIStrategyConverter(strategy=LinearASCIIConversionStrategy())
      case "longLinearBourke" => generatorArguments.ascii_converter = ASCIIStrategyConverter(conversionTable=LongBourkeConversionTable(), strategy=LinearASCIIConversionStrategy())
      case "quadraticBourke" => generatorArguments.ascii_converter = ASCIIStrategyConverter(strategy=QuadraticASCIIConversionStrategy())
      case other => throw InvalidParameterException("invalid parameter: " + other)
      
    next.parse(arguments.filter((arg: Argument) => arg.name == "--table"), generatorArguments)
  }

  override def printUsage(prevUsage: String): String = {
    val usage: String = prevUsage + "[--table (linearBourke | quadraticBourke | longLinearBourke)]\n"
    next.printUsage(prevUsage + usage)
  }
}