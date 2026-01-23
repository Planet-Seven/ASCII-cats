package utils

import org.scalatest.funsuite.AnyFunSuite
import utils.FilePath

class FilePathTest extends AnyFunSuite{
  test("valid path") {
    FilePath("this/is/path")
    assert(true)
  }
  test("single depth path") {
    FilePath("path")
    assert(true)
  }
  test("empty path") {
    assertThrows[RuntimeException](FilePath(""))
  }
  test("invalid path") {
    assertThrows[RuntimeException](FilePath("this  is/not/path"))
  }
  test("path with extension") {
    assertThrows[RuntimeException](FilePath("this/is.path"))
  }
}
