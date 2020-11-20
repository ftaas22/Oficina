class Carro(modelo: String, ano: String,trabalho: Trabalho , aReparar: Boolean) {

  var reparando: Boolean = false
  var pronto: Boolean = false

  def getModel():String={
    return modelo
  }

  def getAno():String= {
    return ano
  }

  def getTrabalho():Trabalho= {
    return trabalho
  }

  def getReparando(): Boolean={
    return reparando
  }

  def setReparando(reparandoCarro: Boolean): Unit ={
    this.reparando=reparandoCarro
  }

  def isPronto(): Boolean = {
    return pronto
  }

  def printCarro(): Unit = {
    println(modelo + " " + ano + " " + trabalho.getAvaria().toString + " " + trabalho.getTempo())
  }

  //passa tempo no carro e marca como arranjado se já passou o tempo necessário
  def reparar() {
    if(trabalho.getTempo() <= 0) {
      pronto = true
    }else{
      trabalho.setTempo(trabalho.getTempo()-1)
    }

  }

}
