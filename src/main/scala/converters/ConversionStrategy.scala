package converters

// The strategy contains the logic used for the conversion according to the provided table
trait ConversionStrategy {
  // converts a single grayscale value [0.0,1.0] to a Char
  def convert(conversionTable: ConversionTable)(value: Double): Char
}