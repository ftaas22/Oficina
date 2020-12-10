import System.atualizarCarros_Mec
import UtilsApp.{download_Cars, download_Mec}

import scala.io.Source

object Teste {
  def main(args: Array[String]): Unit = {
    val source: Iterator[String] = Source.fromFile("src\\carros2.txt").getLines()
    val source2: Iterator[String] = Source.fromFile("src\\mecanicos3.txt").getLines()
    download_Cars(source)
    download_Mec(source2)
    atualizarCarros_Mec()
  }
}
