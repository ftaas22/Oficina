import java.time.{DayOfWeek, LocalDate}

import Mecanico._
import UtilsApp._
import scala.collection.mutable.ListBuffer

case class System(){
  //observaºão precisa de esperar meia hora so depois gerar um random
  //mec disponivel muito tempo no if
  //eficiencia do horario, dar prioridade
  //a reducao das horas
  var dia: LocalDate = LocalDate.now()
  var dayWeek: DayOfWeek = dia.getDayOfWeek
  val entrada = 9
  val saida = 16
  val totaldays = 5

  def getDia: LocalDate = dia

  def setDayWeek(d: LocalDate): Unit = {
    dayWeek = d.getDayOfWeek
  }

  def passarDias(): Unit = {
    while(true) {
      /*for(car <- listaCarros){
        if(!car.isPronto()) {
          //println(car.getModel() + " " + car.isPronto() + " " + car.getTrabalho().getTempo())
        }
      }*/
      //gestaoCarros()
      trabalho()
      //if(allMecanicoIsVazia() && listaCarros.isEmpty) return
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
    print("Acabou o dia   ")

  }

  def diadetrabalho(): Unit = {
    var horasTrabalhadas = 0
    for(_ <- entrada to saida) {
      Thread.sleep(500)
      horasTrabalhadas += 1
      println(horasTrabalhadas)

      Trabalhar(meclist)

      for (mec <- UtilsApp.meclist) {
        if (mec.carro == null || mec.carro.pronto) {
          gestaoCarros(mec)
        }
      }
    }
  }


  def Trabalhar(meclist : ListBuffer[Mecanico]){
    val Temp_mecList = meclist
    def recursiveStep(lst: List[Mecanico]) {
      meclist.clear()
      lst match {
        case Nil =>  lst
        case h :: Nil => meclist += arranjar(h)
        case h :: t => (meclist += arranjar(h))::recursiveStep(t)::Nil
      }}
  }


  //so falta isto
  def gestaoCarros(mec: Mecanico): Unit = {

    var observou = false
    var arranjou = false
    var trabalhou = false
    for (car <- UtilsApp.meclist) {

      if (!trabalhou) {
        if (mec.getEspecializacao().equals(car.getTrabalho().getEspecializacao()) && !car.getReparando() && !arranjou && mec.carro == null) {
          car.setReparando(true)
          mec.setArranjarCarro(car)
          println(mec.getEspecializacao() + "  " + car + "foi atribuido")
          arranjou = true
          trabalhou = true
          mec.setHorasParado(0)
        }
      }
    }
    if (!trabalhou) {
      for (car <- listaCarros) {
        var ava = UtilsApp.randomAvaria()
        if (car.getTrabalho().getAvaria().equals(TipoAvaria.OBSERVACAO) && !observou) {
          car.getTrabalho().setAvaria(ava)
          car.getTrabalho().defineTrabalho()
          car.setReparando(false)
          println("Gerou avaria")
          observou = true
          trabalhou = true
        }
      }

    //ultimo
      for (car <- listaCarros)
        if (mec.getEspecializacao().equals(car.getTrabalho().getEspecializacao()) && !car.getReparando() && !arranjou && mec.getArranjarCarro() != null && existeOutroMecDisponivel()) {
          car.getTrabalho().setEspecializacao(mecDisponivel().getEspecializacao())
          car.getTrabalho().setTempo(car.getTrabalho().getTempo() * 2)
          println("Trabalho nao especializado")
        }

    }
  }



}