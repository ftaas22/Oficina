import scala.io.Source
class System {



}
  object Demo {
    def main(args: Array[String]) {
      val source:Iterator[String] = Source.fromFile("src\\carros.txt").getLines()
      source.foreach(println)


    }
  }

