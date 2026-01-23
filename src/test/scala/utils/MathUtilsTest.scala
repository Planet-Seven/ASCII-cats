package utils

import org.scalatest.funsuite.AnyFunSuite

class MathUtilsTest extends AnyFunSuite{
  test("no clamping") {
    assert(MathUtils.clamp(0.5, 0, 1) == 0.5)
  }

  test("upper clamp") {
    assert(MathUtils.clamp(1.5, 0, 1) == 1.0)
  }

  test("lower clamp") {
    assert(MathUtils.clamp(-2, 0, 1) == 0.0)
  }
}
