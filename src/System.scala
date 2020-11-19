import java.time.{DayOfWeek, LocalDate}

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
    var horasTrabalhadas = 0
    for(_ <- entrada to saida) {
      Thread.sleep(1000)
      horasTrabalhadas += 1
      println(horasTrabalhadas)
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
    while(true) {
      if(listaCarros.isEmpty){
        return
      }
      trabalho()
    }
  }
}