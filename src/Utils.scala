import Avaria._

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
        val trabalho: Trabalho= Trabalho(Avaria.withName(i.split(" ")(2)))
        trabalho.defineTrabalho()
        val car: Carro = Carro(i.split(" ")(0),i.split(" ")(1),trabalho, false)
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

  def randomAvaria(): Avaria = {
     val rad = Avaria(scala.util.Random.nextInt(maxID))
    return rad
  }


  def main(args: Array[String]): Unit = {
    var source:Iterator[String] = Source.fromFile("src\\carros.txt").getLines()
    download_Cars(source)
    println(carlist)
  }

}
