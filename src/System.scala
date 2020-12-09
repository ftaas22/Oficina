import java.time.{DayOfWeek, LocalDate}

import Especializacao.Especializacao
import FxApp.{carlist, meclist}
import Mecanico._

import scala.collection.immutable.Nil.:::
import scala.collection.mutable.ListBuffer

object System{



  def Trabalhar(): Unit = {
    val Temp_mecList = meclist.toList
    meclist.clear()
    def recursiveStep(lst: List[Mecanico]) {
      lst match {
        case h :: Nil => meclist += arranjar(lst.head)
        case h :: t => {
          (meclist += arranjar(lst.head))
          recursiveStep(lst.tail)
        }
      }}
    recursiveStep(Temp_mecList)
    print("\n")
    print(meclist)
  }


  //esta mal tem de adicionar a lista existente do mecaninco não substituila
  def atribEspecializacao(especializacao: Especializacao): Unit = {
    val lmec= meclist.filter(x=> x.especializacao == especializacao).toList
    println(lmec+"confuso")
    meclist=meclist.filterNot(x=> x.especializacao == especializacao)

    val lcar=carlist.filter(x=> x.trabalho.especializacao == especializacao).toList
    carlist=carlist.filterNot(x=> x.trabalho.especializacao == especializacao)

    def recursiveStep(lmec:List[Mecanico],lcar:List[Carro]) {
      if(lmec.length!=0 && lcar.length!=0){
        val n_car_mec=lcar.splitAt(lcar.length/lmec.length)
        if(lmec.head.lista_para_arr!=null){
          val newl=  lmec.head.lista_para_arr ::: n_car_mec._1
          meclist+=lmec.head.copy(lista_para_arr = newl)
        }
        else{
          val newl=n_car_mec._1
          meclist+=lmec.head.copy(lista_para_arr = newl)
        }

        recursiveStep(lmec.tail,n_car_mec._2)
      }
      else {
        null
      }
    }
    recursiveStep(lmec,lcar)
    println(meclist)
  }

  //as observações
  //trabalho com penalidade






}