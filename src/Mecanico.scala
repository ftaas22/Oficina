import Especializacao.Especializacao

class Mecanico(especializacao: Especializacao, salario:String, var arranjarCarro: Carro) {

  override def toString: String = super.toString

  var carro: Carro = arranjarCarro

  def getEspecializacao:Especializacao = {
    especializacao
  }

  def getSalario: String = {
    salario
  }

  def printMecanico(): Unit = {
    println(especializacao + " " + salario)
  }

  /*def getListaCar(): ListBuffer[Carro] = {
    return arranjarCarros
  }*/

  def getArranjarCarro: Carro = {
    carro
  }

  def setArranjarCarro(carroNovo: Carro): Unit = {
    carro = carroNovo
  }

  /*def addCarro(car: Carro): Unit = {
    arranjarCarro += car
  }*/

  //passa tempo no carro a arranjar e limpa carro da lista se já estiver arranjado
  def arranjar() {
    if(carro != null) {
      if (carro.isPronto()) {
        setArranjarCarro(null)
      }else{
        carro.reparar()
      }
    }
  }
}