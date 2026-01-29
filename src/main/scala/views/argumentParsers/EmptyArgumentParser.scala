package views.argumentParsers
import generator.GeneratorArguments

import java.security.InvalidParameterException

class EmptyArgumentParser extends ArgumentParser {
  override def parse(arguments: List[Argument], generatorArguments: GeneratorArguments): GeneratorArguments = {
    if (arguments.nonEmpty) {
      throw InvalidParameterException("Unknown arguments:\n" + arguments.foldLeft("")(
        (acc: String, a: Argument) => acc + a.name + " \n"))
    }
    generatorArguments
  }
  override def printUsage(prevUsage: String): String = prevUsage
}
