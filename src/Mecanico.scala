import Especializacao.Especializacao

class Mecanico(especializacao: Especializacao, salario:String, arranjarCarro: Carro) {

  override def toString: String = super.toString

  var carro: Carro = arranjarCarro

  def getEspecializacao():Especializacao = {
    return especializacao;
  }

  def getSalario(): String = {
    return salario;
  }

  def getArranjarCarro(): Carro = {
    return arranjarCarro
  }

  def setArranjarCarro(carroNovo: Carro): Unit = {
    this.carro = carroNovo
  }
}