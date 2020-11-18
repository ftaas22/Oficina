import scala.io.Source

object Teste{
  def main(args: Array[String]) {
    val source:Iterator[String] = Source.fromFile("src\\carros.txt").getLines()
    source.foreach(println)

  }


}