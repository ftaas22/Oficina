import java.time.LocalDate
import java.time.DayOfWeek

import TipoAvaria._
import Especializacao._

import scala.Console.println


case class Avaria(Tipo_avaria: TipoAvaria){
  //devolver uma lista com posicoes fixas



  /*
     {
      if(avaria.equals(Avaria.BATERIA)) {
        setPreco(100)
        setTempo(2)
        setEspecializacao(Especializacao.ENGELETRICO)
      }
      else if(avar.equals(Avaria.FUSIVEIS)) {
        setPreco(90)
        setTempo(1)
        setEspecializacao(Especializacao.ENGELETRICO)
      }
      else if(avar.equals(Avaria.VELAS)) {
        setPreco(5)
        setTempo(0.5)
        setEspecializacao(Especializacao.ENGELETRICO)
      }
      else if(avar.equals(Avaria.ALTERNADOR)) {
        setPreco(75)
        setTempo(2)
        setEspecializacao(Especializacao.ENGELETRICO)
      }
      else if(avar.equals(Avaria.MOTORES_ELETRICOS)) {
        setPreco(30)
        setTempo(3)
        setEspecializacao(Especializacao.ENGELETRICO)
      }
      else if(avar.equals(Avaria.IGNICAO)) {
        setPreco(20)
        setTempo(3)
        setEspecializacao(Especializacao.ENGELETRICO)
      }
      else if(avar.equals(Avaria.ALMOGADELAS)) {
        setPreco(50)
        setTempo(4)
        setEspecializacao(Especializacao.BATECHAPAS)
      }
      else if(avar.equals(Avaria.ALINHAMENTO_CHASSI)) {
        setPreco(75)
        setTempo(6)
        setEspecializacao(Especializacao.BATECHAPAS)
      }
      else if(avar.equals(Avaria.LAVAR)) {
        setPreco(5)
        setTempo(0.25)
        setEspecializacao(Especializacao.BATECHAPAS)
      }
      else if(avar.equals(Avaria.SUSPENSAO)) {
        setPreco(50)
        setTempo(8)
        setEspecializacao(Especializacao.BATECHAPAS)
      }
      else if(avar.equals(Avaria.TROCA_DE_VIDRO)) {
        setPreco(50)
        setTempo(2)
        setEspecializacao(Especializacao.BATECHAPAS)
      }
      else if(avar.equals(Avaria.PINTAR_PAINEL)) {
        setPreco(100)
        setTempo(1)
        setEspecializacao(Especializacao.PINTOR)
      }
      else if(avar.equals(Avaria.PINTAR_EXTERIOR)) {
        setPreco(300)
        setTempo(8)
        setEspecializacao(Especializacao.PINTOR)
      }
      else if(avar.equals(Avaria.PINTAR_INTEIOR)) {
        setPreco(300)
        setTempo(11)
        setEspecializacao(Especializacao.PINTOR)
      }
      else if(avar.equals(Avaria.MOTOR)) {
        setPreco(3000)
        setTempo(5)
        setEspecializacao(Especializacao.ENGAUTOMOVEL)
      }
      else if(avar.equals(Avaria.ARCONDICIONADO)) {
        setPreco(20)
        setTempo(2)
        setEspecializacao(Especializacao.ENGAUTOMOVEL)
      }
      else if(avar.equals(Avaria.TRANSMISSAO)) {
        setPreco(1000)
        setTempo(3)
        setEspecializacao(Especializacao.ENGAUTOMOVEL)
      }
      else if(avar.equals(Avaria.DIRECAO)) {
        setPreco(50)
        setTempo(1)
        setEspecializacao(Especializacao.ENGAUTOMOVEL)
      }
      else if(avar.equals(Avaria.RODA)) {
        setPreco(100)
        setTempo(0.25)
        setEspecializacao(Especializacao.ENGAUTOMOVEL)
      }
      else if(avar.equals(Avaria.RADIADOR)) {
        setPreco(400)
        setTempo(2)
        setEspecializacao(Especializacao.ENGAUTOMOVEL)
      }
      else if(avar.equals(Avaria.ESCAPE)) {
        setPreco(300)
        setTempo(2)
        setEspecializacao(Especializacao.ENGAUTOMOVEL)
      }
      else if(avar.equals(Avaria.ESTOFOSFRENTE)) {
        setPreco(25)
        setTempo(40)
        setEspecializacao(Especializacao.ESTOFADOR)
      }
      else if(avar.equals(Avaria.ESTOFOSTRAS)) {
        setPreco(25)
        setTempo(40)
        setEspecializacao(Especializacao.ESTOFADOR)
      }
      else if(avar.equals(Avaria.ESTOFOSPORTA)) {
        setPreco(25)
        setTempo(36)
        setEspecializacao(Especializacao.ESTOFADOR)
      }
      else if(avar.equals(Avaria.ESTOFOSBAGAGEM)) {
        setPreco(25)
        setTempo(30)
        setEspecializacao(Especializacao.ESTOFADOR)
      }
      else if(avar.equals(Avaria.CARPETES)) {
        setPreco(25)
        setTempo(28)
        setEspecializacao(Especializacao.ESTOFADOR)
      }
      else if(avar.equals((Avaria.OBSERVACAO))){
        setPreco(0)
        setTempo(0.5)
        setEspecializacao(Especializacao.OBSERVACAO)
      }
    }
    */
}

object Avaria{

  def apply(Tipoavaria: TipoAvaria): Avaria = new Avaria(Tipoavaria)

  //var preco: Double = null
  //var tempo: Double = null
  //var especializacao: Especializacao = null

  def defineTrabalho(x:Avaria):Trabalho={
    x.Tipo_avaria match{
      case TipoAvaria.BATERIA => {
        val Trab= new Trabalho(x.Tipo_avaria,100,2, Especializacao.ENGAUTOMOVEL)
        return Trab
      }
    }
  }


}