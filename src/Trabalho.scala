import java.time.LocalDate
import java.time.DayOfWeek
import Avaria._
import Especializacao._

trait trab {
  var preco: Double
  var tempo: Double
  var especializacao: Especializacao
 // var dia: LocalDate = LocalDate.now()
  //var dayWeek: DayOfWeek = dia.getDayOfWeek
}



class Trabalho(avaria: Avaria) extends trab {

  val entrada = 9
  val saida = 13

  def getAvaria(): Avaria = {
    return avaria
  }

  def getPreco(): Double = {
    return preco
  }

  def setPreco(p:Double) {
    preco = p
  }

  def getTempo(): Double = {
    return tempo
  }

  def setTempo(t:Double) {
    tempo = t
  }

  def getEspecializacao(): Especializacao = {
    return especializacao;
  }

  def setEspecializacao(e:Especializacao) {
    especializacao = e
  }

  def defineTrabalho() {
    if(avaria.equals(Avaria.BATERIA)) {
      setPreco(100)
      setTempo(2)
      setEspecializacao(Especializacao.ENGELETRICO)
    }
    else if(avaria.equals(Avaria.FUSIVEIS)) {
      setPreco(90)
      setTempo(1)
      setEspecializacao(Especializacao.ENGELETRICO)
    }
    else if(avaria.equals(Avaria.VELAS)) {
      setPreco(5)
      setTempo(0.5)
      setEspecializacao(Especializacao.ENGELETRICO)
    }
    else if(avaria.equals(Avaria.ALTERNADOR)) {
      setPreco(75)
      setTempo(2)
      setEspecializacao(Especializacao.ENGELETRICO)
    }
    else if(avaria.equals(Avaria.MOTORES_ELETRICOS)) {
      setPreco(30)
      setTempo(3)
      setEspecializacao(Especializacao.ENGELETRICO)
    }
    else if(avaria.equals(Avaria.IGNICAO)) {
      setPreco(20)
      setTempo(3)
      setEspecializacao(Especializacao.ENGELETRICO)
    }
    else if(avaria.equals(Avaria.ALMOGADELAS)) {
      setPreco(50)
      setTempo(4)
      setEspecializacao(Especializacao.BATECHAPAS)
    }
    else if(avaria.equals(Avaria.ALINHAMENTO_CHASSI)) {
      setPreco(75)
      setTempo(6)
      setEspecializacao(Especializacao.BATECHAPAS)
    }
    else if(avaria.equals(Avaria.LAVAR)) {
      setPreco(5)
      setTempo(0.25)
      setEspecializacao(Especializacao.BATECHAPAS)
    }
    else if(avaria.equals(Avaria.SUSPENSAO)) {
      setPreco(50)
      setTempo(8)
      setEspecializacao(Especializacao.BATECHAPAS)
    }
    else if(avaria.equals(Avaria.TROCA_DE_VIDRO)) {
      setPreco(50)
      setTempo(2)
      setEspecializacao(Especializacao.BATECHAPAS)
    }
    else if(avaria.equals(Avaria.PINTAR_PAINEL)) {
      setPreco(100)
      setTempo(1)
      setEspecializacao(Especializacao.PINTOR)
    }
    else if(avaria.equals(Avaria.PINTAR_EXTERIOR)) {
      setPreco(300)
      setTempo(8)
      setEspecializacao(Especializacao.PINTOR)
    }
    else if(avaria.equals(Avaria.PINTAR_INTEIOR)) {
      setPreco(300)
      setTempo(11)
      setEspecializacao(Especializacao.PINTOR)
    }
    else if(avaria.equals(Avaria.MOTOR)) {
      setPreco(3000)
      setTempo(5)
      setEspecializacao(Especializacao.ENGAUTOMOVEL)
    }
    else if(avaria.equals(Avaria.ARCONDICIONADO)) {
      setPreco(20)
      setTempo(2)
      setEspecializacao(Especializacao.ENGAUTOMOVEL)
    }
    else if(avaria.equals(Avaria.TRANSMISSAO)) {
      setPreco(1000)
      setTempo(3)
      setEspecializacao(Especializacao.ENGAUTOMOVEL)
    }
    else if(avaria.equals(Avaria.DIRECAO)) {
      setPreco(50)
      setTempo(1)
      setEspecializacao(Especializacao.ENGAUTOMOVEL)
    }
    else if(avaria.equals(Avaria.RODA)) {
      setPreco(100)
      setTempo(0.25)
      setEspecializacao(Especializacao.ENGAUTOMOVEL)
    }
    else if(avaria.equals(Avaria.RADIADOR)) {
      setPreco(400)
      setTempo(2)
      setEspecializacao(Especializacao.ENGAUTOMOVEL)
    }
    else if(avaria.equals(Avaria.ESCAPE)) {
      setPreco(300)
      setTempo(2)
      setEspecializacao(Especializacao.ENGAUTOMOVEL)
    }
    else if(avaria.equals(Avaria.ESTOFOSFRENTE)) {
      setPreco(25)
      setTempo(40)
      setEspecializacao(Especializacao.ESTOFADOR)
    }
    else if(avaria.equals(Avaria.ESTOFOSTRAS)) {
      setPreco(25)
      setTempo(40)
      setEspecializacao(Especializacao.ESTOFADOR)
    }
    else if(avaria.equals(Avaria.ESTOFOSPORTA)) {
      setPreco(25)
      setTempo(36)
      setEspecializacao(Especializacao.ESTOFADOR)
    }
    else if(avaria.equals(Avaria.ESTOFOSBAGAGEM)) {
      setPreco(25)
      setTempo(30)
      setEspecializacao(Especializacao.ESTOFADOR)
    }
    else if(avaria.equals(Avaria.CARPETES)) {
      setPreco(25)
      setTempo(28)
      setEspecializacao(Especializacao.ESTOFADOR)
    }
  }

 // def getDia(): LocalDate = return dia

  /*def setDayWeek(d: LocalDate): Unit = {
    dayWeek = d.getDayOfWeek
  }*/

  /*def diadetrabalho(): Unit = {
    var i = 0
    var horasTrabalhadas = 0
    for( i <- entrada to saida ) {
      Thread.sleep(1000)
      horasTrabalhadas += 1
      println(horasTrabalhadas)
    }
  }*/

  /*def trabalho(): Unit = {
    //printDia(dia)
    if(dayWeek == DayOfWeek.SATURDAY) {
    } else if(dayWeek == DayOfWeek.SUNDAY) {
    } else {
      diadetrabalho()
    }
    dia.plusDays(1)
    setDayWeek(dia)
  }*/

  override var preco: Double = _
  override var tempo: Double = _
  override var especializacao: Especializacao = _
  //override var dia: LocalDate = _
  //override var dayWeek: DayOfWeek = _
}