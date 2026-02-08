package views.REST

import conversionTables.{ArrayConversionTable, BourkeConversionTable, LongBourkeConversionTable}
import converters.ASCIIConverterImpl
import exporters.StringExporter
import filters.{BrightnessFilter, FlipFilter, InvertFilter}
import generator.GeneratorArguments
import loaders.CatAPILoader
import org.scalatest.funsuite.AnyFunSuite
import views.REST.RESTView

class RESTViewTest extends AnyFunSuite {

  test("default arguments return Right with default values") {
    val result = RESTView.parseArguments()
    assert(result.isRight)
  }

  test("invalid width returns Left") {
    val result = RESTView.parseArguments(width = Some(0))
    assert(result.isLeft)
  }

  test("brightness out of range returns error") {
    val result = RESTView.parseArguments(brightness = Some(300))
    assert(result.isLeft)
  }

  test("invert true adds InvertFilter") {
    val result = RESTView.parseArguments(invert = Some(true))
    assert(result.isRight)
  }

  test("invalid flip returns error") {
    val result = RESTView.parseArguments(flip = Some("z"))
    assert(result.isLeft)
  }

  test("specifying both conversionTable and customTable returns error") {
    val result = RESTView.parseArguments(conversionTable = Some("Bourke"), customTable = Some("abcd"))
    assert(result.isLeft)
  }

  test("empty customTable returns error") {
    val result = RESTView.parseArguments(customTable = Some(""))
    assert(result.isLeft)
  }

  test("multiple errors are accumulated") {
    val result = RESTView.parseArguments(width = Some(0), flip = Some("z"), brightness = Some(300))
    assert(result.isLeft)
  }
}