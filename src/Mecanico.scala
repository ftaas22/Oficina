import Carro._
import Especializacao._
import UtilsApp._


case class Mecanico(
  especializacao: Especializacao,
  salario:String,
  lista_para_arr: List[Carro],
  nome:String) {

}

object Mecanico{
  def apply(especializacao: Especializacao, salario: String, Lista_para_arr: List[Carro], nome: String): Mecanico = new Mecanico(especializacao, salario, Lista_para_arr, nome)

  //passa tempo no carro a arranjar e limpa carro da lista se já estiver arranjado
  //aqui é que se faz a gestão se o carro esta pronto ou não e se estiver tira lo da lista do mecanico e meter na lista de pronto
  //copy mecanico no arranjar acho
  def arranjar(x: Mecanico):Mecanico ={
     if(x.lista_para_arr!=null) {
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
     else
       return x
  }

  def printMecanico(x: Mecanico): Unit = {
    println(x.especializacao + " " + x.salario + " " + x.nome)
  }

}

