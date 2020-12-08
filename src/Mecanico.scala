import Carro._
import Especializacao._
import UtilsApp._


case class Mecanico(
  nome: String,
  especializacao: Especializacao,
  salario:String,
  lista_para_arr: List[Carro]) {

}

object Mecanico{
  def apply(nome: String, especializacao: Especializacao, salario: String, lista_para_arr: List[Carro]): Mecanico = new Mecanico(nome, especializacao, salario, lista_para_arr)

  //passa tempo no carro a arranjar e limpa carro da lista se já estiver arranjado
  //aqui é que se faz a gestão se o carro esta pronto ou não e se estiver tira lo da lista do mecanico e meter na lista de pronto
  //copy mecanico no arranjar acho
  def arranjar(x: Mecanico):Mecanico ={
      println(x.especializacao)
      x.lista_para_arr.head.print()
      if (x.lista_para_arr.head.pronto()) {
        if(x.lista_para_arr.head.trabalho.TipoAvaria==TipoAvaria.OBSERVACAO) {
          val n_ava = Avaria(UtilsApp.randomAvaria())
          val n_tra= Avaria.defineTrabalho(n_ava)
          carlist+= x.lista_para_arr.head.copy(trabalho= n_tra)
          return x.copy(lista_para_arr=x.lista_para_arr.drop(1))
        }else {
          carlist+= x.lista_para_arr.head
          return x.copy(lista_para_arr=x.lista_para_arr.drop(1))
        }
      }else{
        return x.copy(lista_para_arr=x.lista_para_arr.updated(1,reparar(x.lista_para_arr.head)))
     }
  }

  def printMecanico(x: Mecanico): Unit = {
    println(x.especializacao + " " + x.salario)
  }

}

