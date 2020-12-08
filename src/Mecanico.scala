import Carro._
import Especializacao._
import UtilsApp._


case class Mecanico(
  especializacao: Especializacao,
  salario:String,
  lista_para_arr: List[Carro]) {

}

object Mecanico{
  def apply(especializacao: Especializacao, salario: String, Lista_para_arr: List[Carro]): Mecanico = new Mecanico(especializacao, salario, Lista_para_arr)

  //passa tempo no carro a arranjar e limpa carro da lista se já estiver arranjado
  //aqui é que se faz a gestão se o carro esta pronto ou não e se estiver tira lo da lista do mecanico e meter na lista de pronto
  def arranjar(x: Mecanico) {
    if(x.lista_para_arr.head != null) {
      println(x.especializacao)
      x.lista_para_arr.head.print()
      if (x.lista_para_arr.head.pronto()) {
        carlist+= x.lista_para_arr.head
        x.lista_para_arr.drop(1)
      }else{
        x.lista_para_arr.updated(1,reparar(x.lista_para_arr.head))
      }
    }
  }

  def printMecanico(x: Mecanico): Unit = {
    println(x.especializacao + " " + x.salario)
  }

}

