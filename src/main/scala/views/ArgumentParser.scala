package views

import conversionTables.{BourkeConversionTable, LongBourkeConversionTable, ArrayConversionTable}
import converters.{LinearConversionStrategy, QuadraticConversionStrategy, SingleStrategyConverter}
import exporters.{ConsoleExporter, FileExporter}
import filters.{BrightnessFilter, FlipFilter, InvertFilter}
import generator.GeneratorArguments
import loaders.CatAPILoader

// This is not oop and not extendable. Ideally, it should be implemented using
// the chain of responsibility patter. However, I've got a thesis to write,
// so I'm keeping this abomination for now.

class ArgumentParser {
  def parse(_args: Seq[String]): GeneratorArguments = {
    var pairedArgs: List[Argument] = List()
    var args = _args

    while (args.nonEmpty) {
      val top: String = args.head
      if (top.startsWith("--")) {
        val argument: Argument = Argument(top)
        args = args.tail
        if (args.nonEmpty && !args.head.startsWith("--"))
          argument.setParameter(args.head)
          args = args.tail
        pairedArgs = pairedArgs :+ argument
      }
      else throw RuntimeException("Unknown token: " + top)
    }

    val generatorArguments = GeneratorArguments(
      _imageLoader = CatAPILoader(),
      _filters = Seq(),
      _converter = SingleStrategyConverter(strategy = LinearConversionStrategy()),
      _exporters = Seq()
    )

    pairedArgs.foreach(arg => {
      arg.name match {
        case "--table" =>
          arg.getParameter match
            case "linearBourke" => generatorArguments.converter = SingleStrategyConverter(strategy=LinearConversionStrategy())
            case "longLinearBourke" => generatorArguments.converter = SingleStrategyConverter(conversionTable=LongBourkeConversionTable(), strategy=LinearConversionStrategy())
            case "quadraticBourke" => generatorArguments.converter = SingleStrategyConverter(strategy=QuadraticConversionStrategy())
        case "--custom-table" =>
          generatorArguments.converter = SingleStrategyConverter(ArrayConversionTable(arg.getParameter.toArray), LinearConversionStrategy())
        case "--invert" =>
          generatorArguments.filters = generatorArguments.filters :+ InvertFilter()
        case "--flip" =>
          print(arg.getParameter)
          arg.getParameter match {
            case "x" => generatorArguments.filters = generatorArguments.filters :+ FlipFilter(flipX = true)
            case "y" => generatorArguments.filters = generatorArguments.filters :+ FlipFilter(flipX = false)
            case _ => throw RuntimeException("Invalid flip axis.")}
        case "--brightness" =>
          generatorArguments.filters = generatorArguments.filters :+ BrightnessFilter(arg.getParameter.toDouble / 255.0)
        case "--output-console" =>
          generatorArguments.exporters = generatorArguments.exporters :+ ConsoleExporter()
        case "--output-file" =>
          generatorArguments.exporters = generatorArguments.exporters :+ FileExporter(arg.getParameter)
        case _ => throw RuntimeException("Unknown argument: " + arg.name)
      }
    })
    generatorArguments
  }
}
