import Mecanico._
import scala.collection.mutable.ListBuffer

case class System(){
  //observaºão precisa de esperar meia hora so depois gerar um random
  //mec disponivel muito tempo no if
  //eficiencia do horario, dar prioridade
  //a reducao das horas

  def Trabalhar(meclist : ListBuffer[Mecanico]): Unit = {
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
  /*def gestaoCarros(mec: Mecanico): Unit = {

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
  }*/



}