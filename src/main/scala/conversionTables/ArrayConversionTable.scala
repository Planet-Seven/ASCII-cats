package conversionTables

import converters.ConversionTable
import utils.MathUtils.clamp

class ArrayConversionTable(val table: Array[Char]) extends ConversionTable{
  override def getChar(_i: Int): Char = {
    val i: Int = clamp(_i, 0, table.length - 1).toInt
    table(i)
  }
  override def length: Int = table.length
}
