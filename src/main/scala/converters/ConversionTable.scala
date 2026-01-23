package converters

// represents a conversion table. The table does not contain logic for a conversion,
// that is handled by the convertor itself.
trait ConversionTable {
  def getChar(i: Int): Char
  def length: Int
}