

import Especializacao.Especializacao
import FxApp.{carlist, meclist}
import Mecanico._

import scala.annotation.tailrec

object System{


  def atualizarCarros_Mec(): Unit ={
    esvaziarMec()
    atriEspecializacao(Especializacao.ENGELETRICO)
    atriEspecializacao(Especializacao.ENGAUTOMOVEL)
    atriEspecializacao(Especializacao.BATECHAPAS)
    atriEspecializacao(Especializacao.PINTOR)
    atriEspecializacao(Especializacao.ESTOFADOR)
    atriObservacao()
    atriOutraEspcializacao()
  }


  def esvaziarMec(): Unit ={
    meclist.clear()

  }





  def Trabalhar(): Unit = {
    val Temp_mecList = meclist.toList
    meclist.clear()
    @tailrec
    def recursiveStep(lst: List[Mecanico]) {
      lst match {
        case _ :: Nil => meclist += arranjar(lst.head)
        case _ :: _ =>
          meclist += arranjar(lst.head)
          recursiveStep(lst.tail)
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
      @tailrec
      def recursiveStep(lmec:List[Mecanico], lcar:List[Carro]) {
        if(lmec.nonEmpty && lcar.nonEmpty){
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
    @tailrec
    def recursiveStep(lmec:List[Mecanico], lcar:List[Carro]): Unit ={
      if(lmec.nonEmpty && lcar.nonEmpty){
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
    @tailrec
    def recursiveStep(lmec:List[Mecanico], lcar:List[Carro]): Unit ={
      if(lmec.nonEmpty && lcar.nonEmpty){
        val n_car_mec=lcar.splitAt(lcar.length/lmec.length)

          @tailrec
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