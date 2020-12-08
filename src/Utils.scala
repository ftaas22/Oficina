import java.io.{File, PrintWriter}

import TipoAvaria._
import Trabalho._

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.io.Source

object Utils {
    //metodo de carregar os mecanicos e o carro
    //criar o log aqui

  var carlist= ListBuffer[Carro]()
  var meclist = ListBuffer[Mecanico]()


  @tailrec
  def download_Cars(source:Iterator[String]): Unit ={
   if(source.hasNext){
        val i = source.next()
        val avaria: Avaria =  Avaria(TipoAvaria.withName(i.split(" ")(2)))
        val trabalho = Avaria.defineTrabalho(avaria)
        //val trabalho: Trabalho= Trabalho(TipoAvaria.withName(i.split(" ")(2)))
        //defineTrabalho(avaria)

        val car: Carro = Carro(i.split(" ")(0),i.split(" ")(1),trabalho, false)
        car.copy(ano= "1990")
        carlist+=car
        println(car.toString)
        download_Cars(source:Iterator[String])
      }
    }

  @tailrec
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
    val writer = new PrintWriter(new File("src\\carros3.txt"))
    def writeList(list: List[Carro]): Unit = list match {
      case Nil => list
      case x :: xs => writer.write(x.modelo + " " + x.ano + " " + x.trabalho.TipoAvaria + "\n") :: writeList(xs) :: Nil
    }
    writeList(carlist.toList)
    writer.close()
  }


  def mecListToFile(): Unit = {
    val writer = new PrintWriter(new File("src\\mecanicos3.txt"))
    def writeList(list: List[Mecanico]): Unit = list match {
      case Nil => list
      case x :: xs => writer.write(x.especializacao + " " + x.salario + " " + x.arranjarCarro + "\n") :: writeList(xs) :: Nil
    }
    writeList(meclist.toList)
    writer.close()
  }


  def main(args: Array[String]): Unit = {
    var source: Iterator[String] = Source.fromFile("src\\carros.txt").getLines()
    var source2: Iterator[String] = Source.fromFile("src\\mecanicos.txt").getLines()
    download_Cars(source)
    download_Mec(source2)
    //println(carlist)
    //carListToFile()
    mecListToFile()

  }
}
