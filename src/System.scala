import java.time.{DayOfWeek, LocalDate}

import Especializacao.Especializacao
import FxApp.{carlist, meclist}
import Mecanico._

import scala.::
import scala.collection.immutable.Nil.:::
import scala.collection.mutable.ListBuffer

object System{


  def atualizarCarros_Mec(): Unit ={



  }


  def esvaziarMec(): Unit ={



  }


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
  def atriEspecializacao(especializacao: Especializacao): Unit = {
    if(especializacao!=Especializacao.OBSERVACAO){
      val lmec= meclist.filter(x=> x.especializacao == especializacao).toList
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
        else {}
      }
    recursiveStep(lmec,lcar)
  }
    else{}
  }


  def atriObservacao(): Unit ={
    val lmec= meclist.toList
    meclist.clear()
    val lcar= carlist.filter(x=> x.trabalho.especializacao== Especializacao.OBSERVACAO).toList
    carlist=carlist.filterNot(x=> x.trabalho.especializacao== Especializacao.OBSERVACAO)
    def recursiveStep(lmec:List[Mecanico],lcar:List[Carro]): Unit ={
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
      else {}
    }
    recursiveStep(lmec,lcar)
  }

  def atriOutraEspcializacao(): Unit ={
    val lmec= meclist.toList
    meclist.clear()
    val lcar= carlist.toList
    carlist.clear()
    def recursiveStep(lmec:List[Mecanico],lcar:List[Carro]): Unit ={
      if(lmec.length!=0 && lcar.length!=0){
        val n_car_mec=lcar.splitAt(lcar.length/lmec.length)

          def anotherRecursiveStep(l2: List[Carro], n_l: List[Carro]): List[Carro] = {
            if (l2 != null) {
              val trab = l2.head.trabalho.copy(tempo = l2.head.trabalho.tempo * 2)
              val car = l2.head.copy(trabalho = trab)
              val lc = n_l:::List(car)
              anotherRecursiveStep(l2.tail,lc)
            }
            else{
              n_l
            }
          }
        val a: List[Carro] = List[Carro]()
        val aux= anotherRecursiveStep(n_car_mec._1,a)
        if(lmec.head.lista_para_arr!=null){
          val newl=  lmec.head.lista_para_arr ::: aux
          meclist+=lmec.head.copy(lista_para_arr = newl)
        }
        else{
          val newl=aux
          meclist+=lmec.head.copy(lista_para_arr = newl)
        }

        recursiveStep(lmec.tail,n_car_mec._2)
      }
      else {}
    }
    recursiveStep(lmec,lcar)
  }






  //as observações
  //trabalho com penalidade






}