

import Especializacao.{ENGELETRICO, Especializacao}
import FxApp.{carlist, meclist}
import Mecanico._

import scala.collection.mutable.ListBuffer

object System{


  def atualizarCarros_Mec(): Unit ={
    println(carlist)
    esvaziarMec()
    println(carlist)
    atriEspecializacao(Especializacao.ENGELETRICO)
    println(carlist)
    atriEspecializacao(Especializacao.ENGAUTOMOVEL)
    println(carlist)
    atriEspecializacao(Especializacao.BATECHAPAS)
    println(carlist)
    atriEspecializacao(Especializacao.PINTOR)
    println(carlist)
    atriEspecializacao(Especializacao.ESTOFADOR)
    println(carlist)
    atriObservacao()
    println(carlist)
    atriOutraEspcializacao()
    println(carlist)
  }


  def esvaziarMec(): Unit ={
    val mec= meclist.toList
    meclist.clear()
    def recusiveStep(mec:List[Mecanico]): Unit ={
     if(mec.length!=0){
      def anotherRecursiveStep(lst: List[Carro]) {
        lst match {
          case h :: Nil => carlist += lst.head
          case h :: t => {
            carlist += lst.head
            anotherRecursiveStep(lst.tail)
          }
        }}

      anotherRecursiveStep(mec.head.lista_para_arr)
      val a: List[Carro] = List[Carro]()
      val aux= mec.head.copy(lista_para_arr = a)
      meclist +=aux
      recusiveStep(mec.tail)
      }
    }
    recusiveStep(mec)

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



  def atriEspecializacao(especializacao: Especializacao): Unit = {
    if(especializacao!=Especializacao.OBSERVACAO) {
      val lmec = meclist.filter(x => x.especializacao == especializacao).toList
      if (lmec.length != 0) {
        meclist = meclist.filterNot(x => x.especializacao == especializacao)
        val lcar = carlist.filter(x=>x.trabalho.tempo != 0.0).toList
        val lcar1 = lcar.filter(x => x.trabalho.especializacao == especializacao)
        carlist = carlist.filterNot(x => x.trabalho.especializacao == especializacao)
        def recursiveStep(lmec: List[Mecanico], lcar: List[Carro]) {

          if (lmec.length != 0 && lcar.length != 0) {
            val n_car_mec = lcar.splitAt(lcar.length / lmec.length)

            if (lmec.head.lista_para_arr != null) {
              val newl = lmec.head.lista_para_arr ::: n_car_mec._1
              meclist += lmec.head.copy(lista_para_arr = newl)
            }
            else {
              val newl = n_car_mec._1
              meclist += lmec.head.copy(lista_para_arr = newl)
            }

            recursiveStep(lmec.tail, n_car_mec._2)
          }
          else {}
        }
        recursiveStep(lmec, lcar1)
      }
      else {}
    }
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
    //println("carlist"+carlist)
    val lcar= carlist.filter(x=>x.trabalho.tempo != 0.0).toList
    carlist= carlist.filterNot(x=>x.trabalho.tempo != 0.0)
    def recursiveStep(lmec:List[Mecanico],lcar:List[Carro]): Unit ={
      if(lmec.length!=0 ){
        val n_car_mec=lcar.splitAt(lcar.length/lmec.length)
        //println("lista dos carros para adicionar"+n_car_mec)
          def anotherRecursiveStep(l2: List[Carro], n_l: List[Carro]): List[Carro] = {
            if (l2.length != 0) {
              val trab = l2.head.trabalho.copy(tempo = l2.head.trabalho.tempo * 2)
              println(trab)
              val car = l2.head.copy(trabalho = trab)
              val lc = n_l:::List(car)
              //println(lc+ "lista vai adicionando")
              anotherRecursiveStep(l2.tail,lc)
            }
            else{
              //println("else")
              n_l
            }
          }
        val a: List[Carro] = List[Carro]()
        val aux= anotherRecursiveStep(n_car_mec._1,a)
        //println(aux+ "AUX")
        if(lmec.head.lista_para_arr.length!=0){
          val newl=  lmec.head.lista_para_arr ::: aux

          meclist+=lmec.head.copy(lista_para_arr = newl)
          //println(meclist+"lista do mec NAO estava null")
          //println(meclist)
        }
        else{
          val newl=aux
          meclist+=lmec.head.copy(lista_para_arr = newl)
          //println(meclist+"lista do mec ESTAVA null")
          //println(meclist)
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