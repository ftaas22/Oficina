import Especializacao.Especializacao

class Mecanico(especializacao: Especializacao, salario:String, arranjarCarro: List[Carro]) {

  override def toString: String = super.toString

  var carro: Carro = arranjarCarro

  def getEspecializacao():Especializacao = {
    return especializacao;
  }

  def getSalario(): String = {
    return salario;
  }

  def getArranjarCarro(): Carro = {
    return carro
  }

  def setArranjarCarro(carroNovo: Carro): Unit = {
    this.carro = carroNovo
  }

  def addCarro(car: Carro): Unit = {
    arranjarCarro += car
  }

  //passa tempo no carro a arranjar e limpa carro da lista se já estiver arranjado
  def arranjar() {
    arranjarCarro.head.reparar()
    if(arranjarCarro.head.isPronto()) {
      arranjarCarro = arranjarCarro.tail
    }
  }
}