import java.io.{File, PrintWriter}

import Mecanico._
import TipoAvaria._
import Trabalho._

import scala.::
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.io.Source

object UtilsApp {
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
        val car: Carro = Carro(i.split(" ")(0),i.split(" ")(1),trabalho, i.split(" ")(3))
        //car.copy(ano= "1990")
        carlist+=car
        //println(car.toString)
        download_Cars(source:Iterator[String])
      }
    }

  @tailrec
  def download_Mec(source:Iterator[String]): Unit ={
    if(source.hasNext){
      val i = source.next()
      /*val avaria: Avaria = Avaria(Tipoavaria = OBSERVACAO)
      val trabalho = Avaria.defineTrabalho(avaria)
      val carro: Carro = Carro("BMW","1980",trabalho,"Pedro")
      val carro2: Carro = Carro("Volkswagen","1920",trabalho,"José")
      val arranjarList : List[Carro] = List(carro, carro2)*/

      var mecanico: Mecanico = Mecanico(Especializacao.withName(i.split(" ")(0)), i.split(" ")(1),i.split(" ")(2), null)
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
    val writer = new PrintWriter(new File("src\\carros2.txt"))
    def writeList(list: List[Carro]): Unit = list match {
      case Nil => list
      case x :: xs => writer.write(x.modelo + " " + x.ano + " " +x.trabalho.TipoAvaria + " " + x.dono + "\n") :: writeList(xs) :: Nil
    }
    writeList(carlist.toList)
    writer.close()
  }


  //alterar para dar write da lista de carros
  def mecListToFile(): Unit = {
    val writer = new PrintWriter(new File("src\\mecanicos2.txt"))
    def writeCars(carList: List[Carro]): String = carList match{
      case Nil => ""
      case x :: xs => {
        val string = x.modelo +"_"+x.ano+"_"+x.trabalho.TipoAvaria+"_"+x.trabalho.tempo+"_"+x.dono+" "
        string
      }+writeCars(xs)
    }
    def writeList(list: List[Mecanico]): Unit = list match {
      case Nil => list
      case x :: xs => writer.write(x.especializacao + " " + x.salario + " " + x.nome + " "  + writeCars(x.lista_para_arr) +/*+ x.arranjarCarro*/  /*x.lista_para_arr*/ "\n") :: writeList(xs) :: Nil
    }
    writeList(meclist.toList)
    writer.close()
  }

  def FindCar(modelo:String, ano: String, dono: String): Carro = {
    val temp = carlist.filter(_.modelo == modelo)
    val temp1 = temp.filter(_.ano == ano)
    val temp2 = temp1.filter(_.dono == dono)
    return temp2.head
  }


  def main(args: Array[String]): Unit = {
    var source: Iterator[String] = Source.fromFile("src\\carros.txt").getLines()
    var source2: Iterator[String] = Source.fromFile("src\\mecanicos.txt").getLines()
    download_Cars(source)
    download_Mec(source2)
    //println(carlist)
    carListToFile()
    mecListToFile()
  }
}
