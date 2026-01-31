package utils

import scala.util.matching.Regex

class FilePath(_path: String) {
  private val pathPattern: Regex = "^([a-zA-Z0-9/])*[a-zA-Z0-9]$".r
  require(pathPattern.pattern.matcher(_path).matches)
  private val path: String = _path

  def getPath: String = path
}
