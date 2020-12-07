case class Carro(
  val modelo: String,
  val ano: String,
  val trabalho: Trabalho,
  val aReparar: Boolean){
  var reparando: Boolean = false
  var pronto: Boolean = false

}

object Carro{

  def apply(modelo: String, ano: String, trabalho: Trabalho, aReparar: Boolean): Carro = new Carro(modelo, ano, trabalho, aReparar)



  //passa tempo no carro e marca como arranjado se já passou o tempo necessário
  def reparar(x: Carro) {
    if(x.trabalho.tempo <= 0) {
      x.pronto = true
    }else{
      x.trabalho.setTempo(x.trabalho.getTempo()-1)
    }
  }

  def printCarro(x: Carro): Unit = {
    if(true) {
      println("PRONTO: " + x.modelo + " " + x.ano + " " + x.trabalho.getAvaria().toString + " " + x.trabalho.getTempo())
    } else {
      println("A ARRANJAR " + x.modelo + " " + x.ano + " " + x.trabalho.getAvaria().toString + " " + x.trabalho.getTempo())
    }
  }


}
