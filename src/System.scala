import java.time.{DayOfWeek, LocalDate}

import Avaria.Avaria

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.breakable

class System(listaMecanicos: List[Mecanico], var listaCarros: ListBuffer[Carro]){

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
      Thread.sleep(1000)
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

  def allMecanicoIsVazia(): Boolean = {
    var isVazia: Boolean = false
    for(mec <-listaMecanicos) {
      if(mec.getListaCar().isEmpty)
        isVazia = true
    }
    return isVazia
  }

  //fazer passar dias enquanto houver carros para reparar
  def passarDias(): Unit = {
    while(true) {
      gestaoCarros()
      /*if(!listaCarros.isEmpty){

      }*/
      trabalho()
      /*if(allMecanicoIsVazia()) {
        return
      }*/
    }
  }

  def gestaoObservacao(car: Carro): Unit = {
    var ava = Avaria.randomAvaria()
    car.getTrabalho().setAvaria(ava)
  }

  def gestaoCarros(): Unit = {
    for(mec <-listaMecanicos) {
      for (car <- listaCarros) {
        if (mec.getEspecializacao().equals(car.getTrabalho().especializacao) /*&& mec.getArranjarCarro() == null*/ && car.getReparando() == false) {
          //i.setArranjarCarro(j)
          car.setReparando(true)
          mec.addCarro(car)
          listaCarros = listaCarros.tail
          //println(mec.getEspecializacao())
        } else if(car.getTrabalho().avaria.equals(Avaria.OBSERVACAO)) {
          //gestaoObservacao(car)
          var ava = Avaria.randomAvaria()
          car.getTrabalho().setAvaria(ava)
        }
      }
    }
  }

  def break(mecanico: Mecanico): Unit ={

  }
}