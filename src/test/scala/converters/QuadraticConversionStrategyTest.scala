package converters

import converters.QuadraticConversionStrategy
import org.scalatest.funsuite.AnyFunSuite
import conversionTables.ArrayConversionTable

class QuadraticConversionStrategyTest extends AnyFunSuite{
  private val strategy = QuadraticConversionStrategy()

  test("Min and max values"){
    assert(strategy.convert(ArrayConversionTable(Array('.','X')))(0.0) == '.')
    assert(strategy.convert(ArrayConversionTable(Array('.','X')))(1.0) == 'X')
  }

  test("Middle values"){
    assert(strategy.convert(ArrayConversionTable(Array('.','X')))(0.70) == '.')
    assert(strategy.convert(ArrayConversionTable(Array('.','X')))(0.71) == 'X')
  }

  test("Values outside of [0, 1]") {
    assertThrows[RuntimeException](strategy.convert(ArrayConversionTable(Array('.','X')))(-0.1) == '.')
    assertThrows[RuntimeException](strategy.convert(ArrayConversionTable(Array('.','X')))(1.1) == 'X')
  }
}
