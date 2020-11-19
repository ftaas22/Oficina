import java.time.{DayOfWeek, LocalDate}

class System(listaMecanicos: List[Mecanico]){

  var dia: LocalDate = LocalDate.now()
  var dayWeek: DayOfWeek = dia.getDayOfWeek
  val entrada = 9
  val saida = 13
  val totaldays = 5

  def getDia(): LocalDate = return dia

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

  def passarDias(totaldays: Int): Unit = {
    var i = 0
    for (i <- 0 to totaldays) {
      trabalho()
    }
  }
}