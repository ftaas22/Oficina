import scala.io.Source


class System {
  def main(args: Array[String]) {
    val source:Iterator[String] = Source.fromFile("src\\carros.txt").getLines()
    source.foreach(println)

  }

}
