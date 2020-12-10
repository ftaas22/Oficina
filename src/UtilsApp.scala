import java.io.{File, PrintWriter}

import Avaria.defineTrabalho
import Especializacao.Especializacao
import FxApp.{carlist, meclist}
import System.atriOutraEspcializacao
import TipoAvaria._

import scala.annotation.tailrec

object UtilsApp {
    //metodo de carregar os mecanicos e o carro
    //criar o log aqui




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
      val carArranjarList : List[Carro] = List[Carro]()
      @tailrec
      def listaParaArranjar(i : String, a: Int, b: Int, list: List[Carro]): List[Carro] = (a, b) match{
        case (_,_) =>
          if (a<b) {
            val string = i.split(" ")(a)
            val avaria: Avaria =  Avaria(TipoAvaria.withName(string.split("_")(2)))
            val trabalho = Avaria.defineTrabalho(avaria)
            val trabalho2 = trabalho.copy(tempo = string.split("_")(3).toDouble)
            val car: Carro = Carro(string.split("_")(0), string.split("_")(1), trabalho2, string.split("_")(4))
            listaParaArranjar(i,a+1,b, list.::(car))
          }else{
            list
          }
      }
      val carArranjarList2 = listaParaArranjar(i,3,i.split(" ").length, carArranjarList)
      var mecanico: Mecanico = Mecanico(Especializacao.withName(i.split(" ")(0)), i.split(" ")(1),i.split(" ")(2), carArranjarList2)
      meclist+=mecanico
      println(mecanico)
      download_Mec(source:Iterator[String])
    }
  }//para

  def randomAvaria(): TipoAvaria = {
     val rad = TipoAvaria(scala.util.Random.nextInt(maxID))
    rad
  }

  def carListToFile(): Unit = {
    val writer = new PrintWriter(new File("src\\carros2.txt"))
    def writeList(list: List[Carro]): Unit = list match {
      case Nil =>
      case x :: xs => writer.write(x.modelo + " " + x.ano + " " +x.trabalho.TipoAvaria + " " + x.dono + "\n") :: writeList(xs) :: Nil
    }
    writeList(carlist.toList)
    writer.close()
  }


  //alterar para dar write da lista de carros
  def mecListToFile(): Unit = {
    val writer = new PrintWriter(new File("src\\mecanicos3.txt"))
    def writeCars(carList: List[Carro]): String = carList match{
      case Nil => ""
      case x :: xs => {
        val string = x.modelo +"_"+x.ano+"_"+x.trabalho.TipoAvaria+"_"+x.trabalho.tempo+"_"+x.dono+" "
        string
      }+writeCars(xs)
    }
    def writeList(list: List[Mecanico]): Unit = list match {
      case Nil =>
      case x :: xs => writer.write(x.especializacao + " " + x.salario + " " + x.nome + " "  + writeCars(x.lista_para_arr)+ "\n") :: writeList(xs) :: Nil
    }
    writeList(meclist.toList)
    writer.close()
  }

  def FindCar(modelo:String, ano: String, dono: String, list: List[Carro]): Carro = {
    val temp = list.filter(_.modelo == modelo)
    val temp1 = temp.filter(_.ano == ano)
    val temp2 = temp1.filter(_.dono == dono)
    if(temp2.head != null) temp2.head
    else null
  }

  def FindMec(nome: String, especializacao: Especializacao): Mecanico = {
    val temp = meclist.filter(_.nome == nome)
    val temp1 = temp.filter(_.especializacao == especializacao)
    if(temp1.head != null) temp1.head
    else null
  }

  def FindCarInMec(modelo:String, ano: String, dono: String, list: List[Mecanico]): Carro = list match {
    case x :: xs =>
      val tempcar = FindCar(modelo,ano,dono,x.lista_para_arr)
      if(tempcar == null) {
        val tempcar2 = FindCarInMec(modelo,ano,dono,xs)
        tempcar2
      }
      else tempcar
  }




  def main(args: Array[String]): Unit = {
    //val source2: Iterator[String] = Source.fromFile("src\\mecanicos.txt").getLines()
    //download_Cars(source)
    //download_Mec(source2)
    //println(carlist)
    //carListToFile()
    //mecListToFile()
    val tra= defineTrabalho(Avaria(TipoAvaria.FUSIVEIS))
    val car = Carro("bmw","2002",tra , "João")
    val car2 = Carro("bmw","2002",tra , "João2")
    val car3 = Carro("bmw","2002",tra , "João3")
    val mec= Mecanico(Especializacao.PINTOR, "20", "Antonio", null)
    meclist+=mec
    carlist+=car
    carlist+=car2
    carlist+=car3
    atriOutraEspcializacao()
    println(meclist)
  }



}
