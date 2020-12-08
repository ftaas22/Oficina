import Carro._
import Especializacao._
import Utils._


case class Mecanico(
  especializacao: Especializacao,
  salario:String,
  val Lista_para_arr: List[Carro]) {
  
  

}

object Mecanico{
  def apply(especializacao: Especializacao, salario: String, Lista_para_arr: List[Carro]): Mecanico = new Mecanico(especializacao, salario, Lista_para_arr)

  //passa tempo no carro a arranjar e limpa carro da lista se já estiver arranjado
  //aqui é que se faz a gestão se o carro esta pronto ou não e se estiver tira lo da lista do mecanico e meter na lista de pronto
  def arranjar(x: Mecanico) {
    if(x.Lista_para_arr != null) {
      println(x.especializacao)
      printCarro(x.carro)
      if (x.carro.pronto) {
        x.setArranjarCarro(null)
      }else{
        reparar(x.carro)
      }
    }
  }


  def printMecanico(x: Mecanico): Unit = {
    println(x.especializacao + " " + x.salario)
  }

  def addCarro(car: Carro): Unit = {
  carlist += car
  }

}

