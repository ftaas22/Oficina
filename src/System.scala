

import Especializacao.{ENGELETRICO, Especializacao}
import FxApp.{carlist, meclist}
import Mecanico._

import scala.collection.mutable.ListBuffer

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
    if(Temp_mecList.length!=0){
    meclist.clear()

      def recursiveStep(lst: List[Mecanico]) {
          lst match {
            case h :: Nil => meclist += arranjar(lst.head)
            case h :: t => {
              (meclist += arranjar(lst.head))
              recursiveStep(lst.tail)
            }
        }
      }
    recursiveStep(Temp_mecList)
    }
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
    val lcar= carlist.filter(x=>x.trabalho.tempo != 0.0).toList
    carlist= carlist.filterNot(x=>x.trabalho.tempo != 0.0)
    def recursiveStep(lmec:List[Mecanico],lcar:List[Carro]): Unit ={
      if(lmec.length!=0 ){
        val n_car_mec=lcar.splitAt(lcar.length/lmec.length)
          def anotherRecursiveStep(l2: List[Carro], n_l: List[Carro]): List[Carro] = {
            if (l2.length != 0) {
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
        //println(aux+ "AUX")
        if(lmec.head.lista_para_arr.length!=0){
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


  def sortMenosTempo(mec:Mecanico): Unit = {
    if (mec.lista_para_arr.length != 0) {
      val sortedlist = mec.lista_para_arr.sortBy(_.trabalho.tempo)
      val n_mec= mec.copy(lista_para_arr = sortedlist)
      meclist = meclist.filterNot(x=>x.nome == n_mec.nome && x.especializacao == n_mec.especializacao )
      meclist+=n_mec
    }
  }

  def sortMaisTempo(mec:Mecanico): Unit = {
    if (mec.lista_para_arr.length != 0) {
      val sortedlist = mec.lista_para_arr.sortBy(_.trabalho.tempo)(Ordering[Double].reverse)
      val n_mec= mec.copy(lista_para_arr = sortedlist)
      meclist = meclist.filterNot(x=>x.nome == n_mec.nome && x.especializacao == n_mec.especializacao )
      meclist+=n_mec
    }
  }

  def adicionarCar_Mec_Inicio(mec:Mecanico,car:Carro){
    carlist = carlist.filterNot(x=>x.dono == car.dono && x.trabalho == car.trabalho )
    if(mec.lista_para_arr!=0 && mec.lista_para_arr!=null){
      val newl = List(car):::mec.lista_para_arr
      println(newl)
      val n_mec=mec.copy(lista_para_arr = newl)
      meclist = meclist.filterNot(x=>x.nome == n_mec.nome && x.especializacao == n_mec.especializacao )
      meclist+=n_mec
    }
    else{
      val newl = List(car)
      val n_mec=mec.copy(lista_para_arr = newl)
      meclist = meclist.filterNot(x=>x.nome == n_mec.nome && x.especializacao == n_mec.especializacao )
      meclist+=n_mec
    }
  }

  def adicionarCar_Mec_Final(mec:Mecanico,car:Carro){
    carlist = carlist.filterNot(x=>(x.dono == car.dono )&&(x.trabalho == car.trabalho))
    if(mec.lista_para_arr!=0 && mec.lista_para_arr!=null){
      println(mec.lista_para_arr+"antes")
      val newl = mec.lista_para_arr:::List(car)
      println(newl+"depois")
      val n_mec=mec.copy(lista_para_arr = newl)
      meclist = meclist.filterNot(x=>x.nome == n_mec.nome && x.especializacao == n_mec.especializacao )
      meclist+=n_mec
    }
    else{
      val newl = List(car)
      val n_mec=mec.copy(lista_para_arr = newl)
      meclist = meclist.filterNot(x=>x.nome == n_mec.nome && x.especializacao == n_mec.especializacao )
      meclist+=n_mec
    }
  }

  def removerCarro_Mec(mec:Mecanico,car:Carro): Unit ={
   if(car!=null) {
    val newl = mec.lista_para_arr.filterNot(x=> (x.dono == car.dono)&&(x.trabalho == car.trabalho))
    val n_mec = mec.copy(lista_para_arr = newl)
    meclist = meclist.filterNot(x=>x.nome == n_mec.nome && x.especializacao == n_mec.especializacao)
    meclist += n_mec
    carlist+=car
    }
  }

  def horas_Extra(mec:Mecanico,horas:Double): Unit ={
    if(horas>0){
      meclist = meclist.filterNot(x=>x.nome == mec.nome && x.especializacao == mec.especializacao)
      val hor=horas-0.25
      val n_mec = arranjar(mec)
      meclist +=n_mec
      horas_Extra(n_mec,hor)
    }

  }

  def esvaziar_mec(mec:Mecanico): Unit ={

    def RecursiveStep(lst: List[Carro]) {
      lst match {
        case h :: Nil => carlist += lst.head
        case h :: t => {
          carlist += lst.head
          RecursiveStep(lst.tail)
        }
      }}
    if(mec.lista_para_arr.length!=0 && mec.lista_para_arr!=null ){
      RecursiveStep(mec.lista_para_arr)
      meclist=meclist.filterNot(x=>x.nome == mec.nome && x.especializacao == mec.especializacao)
      val a: List[Carro] = List[Carro]()
      val aux= mec.copy(lista_para_arr = a)
      meclist +=aux
    }
  }
}