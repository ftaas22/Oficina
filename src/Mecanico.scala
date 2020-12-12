import Carro.reparar
import Especializacao.Especializacao
import FxApp.carlist
import UtilsApp._


case class Mecanico(
                     especializacao: Especializacao,
                     salario:String,
                     nome:String,
                     lista_para_arr: List[Carro]) {

}

object Mecanico{
  def apply(especializacao: Especializacao, salario: String, nome: String,  Lista_para_arr: List[Carro]): Mecanico = new Mecanico(especializacao, salario, nome, Lista_para_arr)

  def arranjar(x: Mecanico):Mecanico ={
    if(x.lista_para_arr.length!=0) {
      if (x.lista_para_arr.head.pronto()) {
        if(x.lista_para_arr.head.trabalho.TipoAvaria==TipoAvaria.OBSERVACAO) {
          val n_ava = Avaria(UtilsApp.randomAvaria())
          val n_tra= Avaria.defineTrabalho(n_ava)
          carlist+= x.lista_para_arr.head.copy(trabalho= n_tra)
          x.copy(lista_para_arr=x.lista_para_arr.drop(1))
        }else {
          carlist+= x.lista_para_arr.head
          x.copy(lista_para_arr=x.lista_para_arr.drop(1))
        }
      }else{
        val l1 = reparar(x.lista_para_arr.head) :: x.lista_para_arr.tail
        x.copy(lista_para_arr = l1)
      }
    }
    else
      return x
  }

  def printMecanico(x: Mecanico): Unit = {
    println(x.especializacao + " " + x.salario + " " + x.nome)
  }

}

