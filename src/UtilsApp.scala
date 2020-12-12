import Especializacao.Especializacao
import java.io.{File, PrintWriter}

import Avaria.defineTrabalho
import FxApp.{carlist, meclist}
import Mecanico._
import System.{atriEspecializacao, atriOutraEspcializacao}
import TipoAvaria._
import Trabalho._

import scala.{+:, ::}
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.io.Source

object UtilsApp {

  @tailrec
  def download_Cars(source:Iterator[String]): Unit ={
   if(source.hasNext){
        val i = source.next()
        val avaria: Avaria =  Avaria(TipoAvaria.withName(i.split(" ")(2)))
        val trabalho = Avaria.defineTrabalho(avaria)
     if(i.split(" ").size == 5) {
       val trabalho2 = trabalho.copy(tempo = 0)
       val car: Carro = Carro(i.split(" ")(0),i.split(" ")(1),trabalho2, i.split(" ")(3))
       carlist+=car
     }else{
       val car: Carro = Carro(i.split(" ")(0),i.split(" ")(1),trabalho, i.split(" ")(3))
       carlist+=car
     }

        download_Cars(source:Iterator[String])
      }
    }

  @tailrec
  def download_Mec(source:Iterator[String]): Unit ={
    if(source.hasNext){
      val i = source.next()
      val carArranjarList : List[Carro] = List[Carro]()
      def listaParaArranjar(i : String, a: Int, b: Int, list: List[Carro]): List[Carro] = (a, b) match{
        case (_,_) => {
          if (a<b) {
            val string = i.split(" ")(a)
            val avaria: Avaria =  Avaria(TipoAvaria.withName(string.split("//")(2)))
            val trabalho = Avaria.defineTrabalho(avaria)
            val trabalho2 = trabalho.copy(tempo = string.split("//")(3).toDouble)
            val car: Carro = Carro(string.split("//")(0), string.split("//")(1), trabalho2, string.split("//")(4))
            listaParaArranjar(i,a+1,b, list.::(car))
          }else{
            list
          }
        }
      }
      val carArranjarList2 = listaParaArranjar(i,3,i.split(" ").size, carArranjarList)
      var mecanico: Mecanico = Mecanico(Especializacao.withName(i.split(" ")(0)), i.split(" ")(1),i.split(" ")(2), carArranjarList2)
      meclist+=mecanico
      download_Mec(source:Iterator[String])
    }
  }//para

  def randomAvaria(): TipoAvaria = {
     val rad = TipoAvaria(scala.util.Random.nextInt(maxID))
    return rad
  }

  def carListToFile(): Unit = {
    val writer = new PrintWriter(new File("src\\Base De Dados\\carros.txt"))
    def writeList(list: List[Carro]): Unit = list match {
      case Nil => list
      case x :: xs =>{
        if(x.trabalho.tempo <=0) {
          writer.write(x.modelo + " " + x.ano + " " +x.trabalho.TipoAvaria + " " + x.dono + " PRONTO" + "\n")
        }else{
          writer.write(x.modelo + " " + x.ano + " " +x.trabalho.TipoAvaria + " " + x.dono + "\n")
        }
      }:: writeList(xs) :: Nil
    }
    writeList(carlist.toList)
    writer.close()
  }



  def mecListToFile(): Unit = {
    val writer = new PrintWriter(new File("src\\Base De Dados\\mecanicos.txt"))
    def writeCars(carList: List[Carro]): String = carList match{
      case Nil => ""
      case x :: xs => {
        val string = x.modelo +"//"+x.ano+"//"+x.trabalho.TipoAvaria+"//"+x.trabalho.tempo+"//"+x.dono+" "
        string
      }+writeCars(xs)
    }
    def writeList(list: List[Mecanico]): Unit = list match {
      case Nil => list
      case x :: xs => {
        if(x.lista_para_arr == null){
          writer.write(x.especializacao + " " + x.salario + " " + x.nome + "\n")
        }else{
          writer.write(x.especializacao + " " + x.salario + " " + x.nome + " "  + writeCars(x.lista_para_arr)+ "\n")
        }
      } :: writeList(xs) :: Nil
    }
    writeList(meclist.toList)
    writer.close()
  }

  def FindCar(modelo:String, ano: String, dono: String, list: List[Carro]): Carro = {
    val temp = list.filter(_.modelo == modelo)
    val temp1 = temp.filter(_.ano == ano)
    val temp2 = temp1.filter(_.dono == dono)
    if(temp2 != Nil) temp2.head
    else null
  }

  def FindMec(nome: String, especializacao: Especializacao): Mecanico = {
    val temp = meclist.filter(_.nome == nome)
    val temp1 = temp.filter(_.especializacao == especializacao)
    if(temp1 != Nil) temp1.head
    else null
  }

  def FindCarInMec(modelo:String, ano: String, dono: String, list: List[Mecanico]): Carro = list match {
    case Nil => null
    case x :: xs => {
      val tempcar = FindCar(modelo,ano,dono,x.lista_para_arr)
      if(tempcar == null) {
        val tempcar2 = FindCarInMec(modelo,ano,dono,xs)
        tempcar2
      }
      else tempcar
    }
  }

  def FindInAll(modelo:String, ano: String, dono: String): Carro = {
    val car = FindCarInMec(modelo,ano,dono,meclist.toList)
    if(car != null) car
    else {
      val temp = FindCar(modelo,ano,dono,carlist.toList)
      temp
    }
  }

  def getCarList(): List[Carro] ={
    carlist.toList
  }

  def getMecList(): List[Mecanico] ={
    meclist.toList
  }

}
