import java.time.{DayOfWeek, LocalDate}

import scala.collection.mutable.ListBuffer

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

  def allMecanicoIsVazia(): Boolean = {
    var isVazia: Boolean = true
    for(mec <-listaMecanicos) {
      if(mec.getArranjarCarro() == null)
        isVazia = false
    }
    isVazia
  }

  def existeOutroMecDisponivel(): Boolean = {
    for(mec <- listaMecanicos) {
      if(mec.getHorasParado() > 8) {
        return true
      }
    }
    return false
  }

  def mecDisponivel(): Mecanico = {
    var mecanicotemp: Mecanico = null
    for(mec <- listaMecanicos) {
      if(mec.getHorasParado() > 8) {
        mecanicotemp = mec
      }
    }
    return mecanicotemp
  }

  //def de um dia de trabalho //alterei para correr o arranjo dos carros por mecanico
  def diadetrabalho(): Unit = {
    var horasTrabalhadas = 0
    for(_ <- entrada to saida) {
      Thread.sleep(1000)
      horasTrabalhadas += 1
      println(horasTrabalhadas)
      for(mec <- listaMecanicos) {
        if(mec.carro == null || mec.carro.isPronto()) {
          gestaoCarros()
        }
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

  def gestaoCarros(): Unit = {
    for(mec <-listaMecanicos) {
      if(mec.carro == null) {
        var trabalhou = false
        var observou = false
        var arranjou = false
        var ava = Avaria.randomAvaria()
        for (car <- listaCarros) {
          if (!trabalhou) {
            if (mec.getEspecializacao().equals(car.getTrabalho().getEspecializacao()) && !car.getReparando() && !arranjou && mec.getArranjarCarro() == null) {
              car.setReparando(true)
              mec.setArranjarCarro(car)
              arranjou = true
              trabalhou = true
              mec.setHorasParado(0)
            } else if (car.getTrabalho().getAvaria().equals(Avaria.OBSERVACAO) && !observou) {
                car.getTrabalho().setAvaria(ava)
                car.getTrabalho().defineTrabalho()
                car.setReparando(false)
                observou = true
                trabalhou = true
            }

            if(mec.getEspecializacao().equals(car.getTrabalho().getEspecializacao()) && !car.getReparando() && !arranjou && mec.getArranjarCarro() != null && existeOutroMecDisponivel()) {
              car.getTrabalho().setEspecializacao(mecDisponivel().getEspecializacao())
              car.getTrabalho().setTempo(car.getTrabalho().getTempo() * 2)
            }
            }
         }
       }
      if(mec.carro == null) mec.setHorasParado(mec.getHorasParado()+1)
      /*if(mec.carro != null){
        println("A ARRANJAR" + " " + mec.carro.getModel() + " " + mec.carro.getTrabalho().getTempo())
      }*/
    }
  }

}