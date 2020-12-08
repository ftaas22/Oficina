import java.io.{File, PrintWriter}

import TipoAvaria._
import Trabalho._

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Utils {
    //metodo de carregar os mecanicos e o carro
    //criar o log aqui

  var carlist= ListBuffer[Carro]()
  var meclist = ListBuffer[Mecanico]()

  def download_Cars(source:Iterator[String]): Unit ={
   if(source.hasNext){
        val i = source.next()
       //val trabalho: Trabalho= Trabalho(TipoAvaria.withName(i.split(" ")(2)))
        //defineTrabalho(trabalho)

        val car: Carro = Carro(i.split(" ")(0),i.split(" ")(1),trabalho, false)
        car.copy(ano= "1990")
        carlist+=car
        println(car.toString)
        download_Cars(source:Iterator[String])
      }
    }

  def download_Mec(source:Iterator[String]): Unit ={
    if(source.hasNext){
      val i = source.next()
      var mecanico: Mecanico = Mecanico(Especializacao.withName(i.split(" ")(0)), i.split(" ")(1), null)
      meclist+=mecanico
      println(mecanico)
      download_Mec(source:Iterator[String])
    }
  }

  def randomAvaria(): TipoAvaria = {
     val rad = TipoAvaria(scala.util.Random.nextInt(maxID))
    return rad
  }

  def carListToFile(): Unit = {
    val writer = new PrintWriter(new File("src\carros2.txt"))
    val carList2 = carlist.toList
    def writeList[A](list: List[Carro]): Unit = list match {
      case Nil => list
      case x::xs => {
        writer.write(x.modelo + " " + x.ano + " " + x.trabalho.avaria + "\n" )}:: writeList(xs) :: Nil
    }

  def main(args: Array[String]): Unit = {
    var source:Iterator[String] = Source.fromFile("src\\carros.txt").getLines()
    download_Cars(source)
    println(carlist)
    carListToFile()
  }

}
