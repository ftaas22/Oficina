class Carro(modelo: String, ano: String,trabalho: Trabalho , aReparar: Boolean) {

  var reparando: Boolean = false
  var pronto: Boolean = false
  var temporestante = trabalho.getTempo()

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
    println(modelo + " " + ano + " " + trabalho.getAvaria().toString + " " + temporestante)
  }

  //passa tempo no carro e marca como arranjado se já passou o tempo necessário
  def reparar() {
    temporestante -= 1
    if(temporestante <= 0) {
      pronto = true
    }
    printCarro()
  }

}
