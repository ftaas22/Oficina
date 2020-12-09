import Avaria.defineTrabalho
import TipoAvaria._
import Especializacao.Especializacao
import FxApp.{carlist, meclist}
import UtilsApp._
//import javafx.stage.Stage

import scala.collection.convert.ImplicitConversions.`collection asJava`
import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.control.Breaks.break
//new commit
object Teste{
  def main(args: Array[String]) {
    var source:Iterator[String] = Source.fromFile("src\\carros.txt").getLines()
    var source2:Iterator[String] = Source.fromFile("src\\mecanicos.txt").getLines()



  }
}