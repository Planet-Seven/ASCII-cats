package utils

import scala.util.matching.Regex

class FilePath(_path: String) {
  private val pathPattern: Regex = "^([a-zA-Z0-9/])*[a-zA-Z0-9]$".r
  if (!pathPattern.pattern.matcher(_path).matches)
    throw RuntimeException("Invalid path")
  private val path: String = _path

  def getPath: String = path
}
