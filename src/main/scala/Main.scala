import generator.ASCIIArtGenerator
import views.console.{ConsoleView, View}

@main def main(args: String*): Unit = {
  val generator = ASCIIArtGenerator()
  val view: View = ConsoleView(generator, args)
  view.render()
}