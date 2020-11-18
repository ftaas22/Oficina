import Especializacao.Especializacao

class Mecanico(especializacao: Especializacao, salario:String ) {
  //tirei o horario: Horario do construtor

  override def toString: String = super.toString

  def getEspecializacao():Especializacao = {
    return especializacao;
  }

  def getSalario(): String = {
    return salario;
  }

  //def getHorario(): Horario = {
    //return horario;
  //}
// 8 slots por dia
}