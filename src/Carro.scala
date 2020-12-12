case class Carro(
  modelo: String,
  ano: String,
  trabalho: Trabalho,
  dono:String){

  def pronto():Boolean={
    if(trabalho.tempo <= 0.25)
      true
    else
      false
  }

  override def toString: String = modelo + " " + ano + " " + trabalho.TipoAvaria.toString + " " + trabalho.tempo + " " + dono

  def print(): Unit = {
    println(toString)
  }
}

object Carro{

  def apply(modelo: String, ano: String, trabalho: Trabalho , dono: String): Carro = new Carro(modelo, ano,trabalho, dono)

  def reparar(x: Carro):Carro = {
      val Temp= x.trabalho.tempo- 0.25
      val NovoTrabalho= x.trabalho.copy(tempo=Temp)
      x.copy(trabalho=NovoTrabalho)
  }

}
