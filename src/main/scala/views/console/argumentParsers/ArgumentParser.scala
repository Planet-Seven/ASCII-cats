package views.console.argumentParsers

import generator.GeneratorArguments

trait ArgumentParser {
  def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments
  def printUsage(prevUsage: String): String
}