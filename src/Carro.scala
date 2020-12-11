import javafx.beans.property.{SimpleStringProperty, StringProperty}

import scala.util.Try

case class Carro(
  modelo: String,
  ano: String,
  trabalho: Trabalho,
  dono:String){

  def pronto():Boolean={
    if(trabalho.tempo <= 0)
      true
    else
      false
  }

  override def toString: String = modelo + " " + ano + " " + trabalho.TipoAvaria.toString + " " + trabalho.tempo + " " + dono

  def print(): Unit = {
    println(toString)
  }

  def modeloProperty: StringProperty = new SimpleStringProperty(this.modelo)
  def anoProperty: StringProperty = new SimpleStringProperty(this.ano)
  def donoProperty: StringProperty = new SimpleStringProperty(this.dono)
}

object Carro{

  def apply(modelo: String, ano: String, trabalho: Trabalho , dono: String): Carro = new Carro(modelo, ano,trabalho, dono)

  //passa tempo no carro e marca como arranjado se já passou o tempo necessário
  def reparar(x: Carro):Carro = {
      val Temp= x.trabalho.tempo- 0.25
      val NovoTrabalho= x.trabalho.copy(tempo=Temp)
      x.copy(trabalho=NovoTrabalho)
  }

}
