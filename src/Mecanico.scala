import Carro._
import Especializacao._
import Utils._


case class Mecanico(
  especializacao: Especializacao,
  salario:String,
  var arranjarCarro: Carro) {

  override def toString: String = super.toString

  var carro: Carro = arranjarCarro
  var horasParado: Int = 0


  /*def getListaCar(): ListBuffer[Carro] = {
    return arranjarCarros
  }*/

  def getArranjarCarro(): Carro = {
    carro
  }

  def setArranjarCarro(carroNovo: Carro): Unit = {
    carro = carroNovo
  }

  def getHorasParado(): Int = {
    return this.horasParado
  }

  def setHorasParado(i: Int): Unit = {
    this.horasParado = i
  }


}

object Mecanico{
  def apply(especializacao: Especializacao, salario: String, arranjarCarro: Carro): Mecanico = new Mecanico(especializacao, salario, arranjarCarro)

  //passa tempo no carro a arranjar e limpa carro da lista se já estiver arranjado
  def arranjar(x: Mecanico) {
    if(x.carro != null) {
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

