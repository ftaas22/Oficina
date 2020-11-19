import java.time.{DayOfWeek, LocalDate}

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.breakable

class System(listaMecanicos: List[Mecanico], listaCarros: ListBuffer[Carro]){

  var dia: LocalDate = LocalDate.now()
  var dayWeek: DayOfWeek = dia.getDayOfWeek
  val entrada = 9
  val saida = 16
  val totaldays = 5

  def getDia: LocalDate = dia

  def setDayWeek(d: LocalDate): Unit = {
    dayWeek = d.getDayOfWeek
  }

  //def de um dia de trabalho //alterei para correr o arranjo dos carros por mecanico
  def diadetrabalho(): Unit = {
    var horasTrabalhadas = 0
    for(_ <- entrada to saida) {
      Thread.sleep(3000)
      horasTrabalhadas += 1
      println(horasTrabalhadas)
      for(mec <- listaMecanicos) {
        mec.arranjar()
      }
    }
  }

  //só se trabalha em dias úteis
  def trabalho() {
    println(dia.toString)
    if (dayWeek == DayOfWeek.SATURDAY) {
    } else if (dayWeek == DayOfWeek.SUNDAY) {
    } else {
      diadetrabalho()
    }
    dia = dia.plusDays(1)
    setDayWeek(dia)
  }

  //fazer passar dias enquanto houver carros para reparar
  def passarDias(): Unit = {
    gestaoCarros()
    while(true) {
      /*if(listaCarros.isEmpty){
        return
      }*/
      trabalho()
    }
  }

  def gestaoCarros(): Unit = {
    for(mec <-listaMecanicos) {
      for (car <- listaCarros) {
        if (mec.getEspecializacao().equals(car.getTrabalho().especializacao) /*&& mec.getArranjarCarro() == null*/ && car.getReparando() == false) {
          //i.setArranjarCarro(j)
          car.setReparando(true)
          mec.addCarro(car)
          //println(mec.getEspecializacao())
        }
      }
    }
  }

  def break(mecanico: Mecanico): Unit ={

  }
}