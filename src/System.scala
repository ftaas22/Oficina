import java.time.{DayOfWeek, LocalDate}

import scala.util.control.Breaks.break

class System(listaMecanicos: List[Mecanico], listaCarros: List[Carro]){

  var dia: LocalDate = LocalDate.now()
  var dayWeek: DayOfWeek = dia.getDayOfWeek
  val entrada = 9
  val saida = 13
  val totaldays = 5

  def getDia: LocalDate = dia

  def setDayWeek(d: LocalDate): Unit = {
    dayWeek = d.getDayOfWeek
  }

  //def de um dia de trabalho
  def diadetrabalho(): Unit = {
    var i = 0
    var horasTrabalhadas = 0
    for( i <- entrada to saida ) {
      Thread.sleep(1000)
      horasTrabalhadas += 1
      println(horasTrabalhadas)
    }
  }

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

  def passarDias(): Unit = {
    while(true) {
      if(listaCarros.isEmpty){
        return
      }
      trabalho()
    }

  }
}