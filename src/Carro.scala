class Carro(modelo: String, ano: String,trabalho: Trabalho , aReparar: Boolean) {

  var reparando: Boolean = false

  def getModel():String={
    return modelo
  }

  def getAno():String= {
    return ano
  }

  def getTrabalho():Trabalho= {
    return trabalho
  }

  def setReparando(reparandoCarro: Boolean): Unit ={
    reparando=reparandoCarro
  }

}
