import java.time.LocalDate
import java.time.DayOfWeek

import TipoAvaria._
import Especializacao._

import scala.Console.println


case class Trabalho(
  TipoAvaria: TipoAvaria,
  preco: Double,
  tempo: Double,
  especializacao: Especializacao ){

}

object Trabalho{

  def apply(TipoAvaria: TipoAvaria, preco: Double, tempo: Double, especializacao: Especializacao): Trabalho = new Trabalho(TipoAvaria, preco, tempo, especializacao)

}