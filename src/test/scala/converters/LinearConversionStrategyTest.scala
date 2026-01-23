package converters

import org.scalatest.funsuite.AnyFunSuite

import converters.LinearASCIIConversionStrategy
import conversionTables.ArrayConversionTable

class LinearConversionStrategyTest extends AnyFunSuite {
  private val strategy = LinearASCIIConversionStrategy()

  test("Min and max values"){
    assert(strategy.convert(ArrayConversionTable(Array('.','X')))(0.0) == '.')
    assert(strategy.convert(ArrayConversionTable(Array('.','X')))(1.0) == 'X')
  }

  test("Middle values"){
    assert(strategy.convert(ArrayConversionTable(Array('.','X')))(0.49) == '.')
    assert(strategy.convert(ArrayConversionTable(Array('.','X')))(0.51) == 'X')
  }

  test("Values outside of [0, 1]") {
    assertThrows[RuntimeException](strategy.convert(ArrayConversionTable(Array('.','X')))(-0.1) == '.')
    assertThrows[RuntimeException](strategy.convert(ArrayConversionTable(Array('.','X')))(1.1) == 'X')
  }
}
