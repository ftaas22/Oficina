case class Carro(
  modelo: String,
  ano: String,
  trabalho: Trabalho){

  def pronto():Boolean={
    if(trabalho.tempo <= 0)
      return true
    else
      return false
  }

  override def toString: String = "A ARRANJAR " + modelo + " " + ano + " " + trabalho.TipoAvaria.toString + " " + trabalho.tempo

  def print(): Unit = {
    println(toString)
  }
}

object Carro{

  def apply(modelo: String, ano: String, trabalho: Trabalho, aReparar: Boolean): Carro = new Carro(modelo, ano, trabalho, aReparar)

  //passa tempo no carro e marca como arranjado se já passou o tempo necessário
  def reparar(x: Carro):Carro ={
      val Temp= x.trabalho.tempo-1
      val NovoTrabalho= x.trabalho.copy(tempo=Temp)
      return x.copy(trabalho=NovoTrabalho)
  }

}
