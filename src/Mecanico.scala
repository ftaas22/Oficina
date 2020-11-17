import Especializacao.Especializacao

class Mecanico(especializacao: Especializacao, salario:String, horario: Horario ) {

  override def toString: String = super.toString

  def getEspecializacao():Especializacao = {
    return especializacao;
  }

  def getSalario(): String = {
    return salario;
  }

  def getHorario(): Horario = {
    return horario;
  }

}