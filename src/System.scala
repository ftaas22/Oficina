import java.time.{DayOfWeek, LocalDate}

import Mecanico._

import scala.collection.mutable.ListBuffer

/*case class System(){
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
  }*/

  /*def allMecanicoIsVazia(): Boolean = {
    var isVazia: Boolean = true
    for(mec <-UtilsApp.meclist) {
      if(mec.getArranjarCarro() == null)
        isVazia = false
    }
    isVazia
  }*/

  /*def existeOutroMecDisponivel(): Boolean = {
    for(mec <- Utils.meclist) {
      if(mec.getHorasParado() > 8) {
        return true
      }
    }
    return false
  }*/

  /*def mecDisponivel(): Mecanico = {
    var mecanicotemp: Mecanico = null
    for(mec <- Utils.meclist) {
      if(mec.getHorasParado() > 8) {
        mecanicotemp = mec
      }
    }
    return mecanicotemp
  }*/


  /*def passarDias(): Unit = {
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
  }*/



  //só se trabalha em dias úteis
  /*def trabalho() {
    println(dia.toString)
    if (dayWeek == DayOfWeek.SATURDAY) {
    } else if (dayWeek == DayOfWeek.SUNDAY) {
    } else {
      diadetrabalho()
    }
    dia = dia.plusDays(1)
    setDayWeek(dia)
    print("Acabou o dia   ")

  }*/

  //def de um dia de trabalho //alterei para correr o arranjo dos carros por mecanico
  /*def diadetrabalho(): Unit = {
    var horasTrabalhadas = 0
    for(_ <- entrada to saida) {
      Thread.sleep(500)
      horasTrabalhadas += 1
      println(horasTrabalhadas)

      for (mec <- UtilsApp.meclist)
        arranjar(mec)

      for (mec <- UtilsApp.meclist)
        if (mec.carro == null || mec.carro.pronto)
          gestaoCarros(mec)
    }
  }*/

  //refazer os mecanicos com a lista de carros
  /*def gestaoCarros(mec: Mecanico): Unit = {

    var trabalhou = false
    var observou = false
    var arranjou = false

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


      for (car <- listaCarros)
        if (mec.getEspecializacao().equals(car.getTrabalho().getEspecializacao()) && !car.getReparando() && !arranjou && mec.getArranjarCarro() != null && existeOutroMecDisponivel()) {
          car.getTrabalho().setEspecializacao(mecDisponivel().getEspecializacao())
          car.getTrabalho().setTempo(car.getTrabalho().getTempo() * 2)
          println("Trabalho nao especializado")
        }

    }
  }



}*/