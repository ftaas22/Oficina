import TipoAvaria._
import Especializacao.Especializacao
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



    //adicionar carros a uma lista
    download_Cars(source)
    println(carlist)
    //adicionar mecânicos a uma lista
    download_Mec(source2)
    println(meclist)


    //criar o sistema que faz correr os dias
    //var system = new System()
    //system.passarDias()


  }
}