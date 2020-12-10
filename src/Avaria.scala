import TipoAvaria._


case class Avaria(Tipo_avaria: TipoAvaria){
}

object Avaria{
  def apply(Tipoavaria: TipoAvaria): Avaria = new Avaria(Tipoavaria)


  def defineTrabalho(x:Avaria):Trabalho={
    x.Tipo_avaria match{
      case TipoAvaria.BATERIA =>
        val Trab= new Trabalho(x.Tipo_avaria,100,2, Especializacao.ENGAUTOMOVEL)
        Trab
      case TipoAvaria.FUSIVEIS =>
        val Trab= new Trabalho(x.Tipo_avaria, 90, 2, Especializacao.ENGELETRICO)
        Trab
      case TipoAvaria.VELAS =>
        val Trab= new Trabalho(x.Tipo_avaria, 5, 0.5, Especializacao.ENGELETRICO)
        Trab
      case TipoAvaria.ALTERNADOR =>
        val Trab= new Trabalho(x.Tipo_avaria, 75, 2, Especializacao.ENGELETRICO)
        Trab
      case TipoAvaria.MOTORES_ELETRICOS =>
        val Trab= new Trabalho(x.Tipo_avaria, 30, 3, Especializacao.ENGELETRICO)
        Trab
      case TipoAvaria.IGNICAO =>
        val Trab= new Trabalho(x.Tipo_avaria, 20, 3, Especializacao.ENGELETRICO)
        Trab
      case TipoAvaria.ALMOGADELAS =>
        val Trab= new Trabalho(x.Tipo_avaria, 50, 4, Especializacao.BATECHAPAS)
        Trab
      case TipoAvaria.ALINHAMENTO_CHASSI =>
        val Trab= new Trabalho(x.Tipo_avaria, 75, 6, Especializacao.BATECHAPAS)
        Trab
      case TipoAvaria.LAVAR =>
        val Trab= new Trabalho(x.Tipo_avaria, 5, 0.25, Especializacao.BATECHAPAS)
        Trab
      case TipoAvaria.SUSPENSAO =>
        val Trab= new Trabalho(x.Tipo_avaria, 50, 8, Especializacao.BATECHAPAS)
        Trab
      case TipoAvaria.TROCA_DE_VIDRO =>
        val Trab= new Trabalho(x.Tipo_avaria, 50, 2, Especializacao.BATECHAPAS)
        Trab
      case TipoAvaria.PINTAR_PAINEL =>
        val Trab= new Trabalho(x.Tipo_avaria, 100, 1, Especializacao.PINTOR)
        Trab
      case TipoAvaria.PINTAR_EXTERIOR =>
        val Trab= new Trabalho(x.Tipo_avaria, 300, 8, Especializacao.PINTOR)
        Trab
      case TipoAvaria.PINTAR_INTEIOR =>
        val Trab= new Trabalho(x.Tipo_avaria, 300, 11, Especializacao.PINTOR)
        Trab
      case TipoAvaria.MOTOR =>
        val Trab= new Trabalho(x.Tipo_avaria, 3000, 5, Especializacao.ENGAUTOMOVEL)
        Trab
      case TipoAvaria.ARCONDICIONADO =>
        val Trab= new Trabalho(x.Tipo_avaria, 20, 2, Especializacao.ENGAUTOMOVEL)
        Trab
      case TipoAvaria.TRANSMISSAO =>
        val Trab= new Trabalho(x.Tipo_avaria, 1000, 3, Especializacao.ENGAUTOMOVEL)
        Trab
      case TipoAvaria.DIRECAO =>
        val Trab= new Trabalho(x.Tipo_avaria, 50, 1, Especializacao.ENGAUTOMOVEL)
        Trab
      case TipoAvaria.RODA =>
        val Trab= new Trabalho(x.Tipo_avaria, 100, 0.25, Especializacao.ENGAUTOMOVEL)
        Trab
      case TipoAvaria.RADIADOR =>
        val Trab= new Trabalho(x.Tipo_avaria, 400, 2, Especializacao.ENGAUTOMOVEL)
        Trab
      case TipoAvaria.ESCAPE =>
        val Trab= new Trabalho(x.Tipo_avaria, 300, 2, Especializacao.ENGAUTOMOVEL)
        Trab
      case TipoAvaria.ESTOFOSFRENTE =>
        val Trab= new Trabalho(x.Tipo_avaria, 25, 40, Especializacao.ESTOFADOR)
        Trab
      case TipoAvaria.ESTOFOSTRAS =>
        val Trab= new Trabalho(x.Tipo_avaria, 25, 40, Especializacao.ESTOFADOR)
        Trab
      case TipoAvaria.ESTOFOSPORTA =>
        val Trab= new Trabalho(x.Tipo_avaria, 25, 36, Especializacao.ESTOFADOR)
        Trab
      case TipoAvaria.ESTOFOSBAGAGEM =>
        val Trab= new Trabalho(x.Tipo_avaria, 25, 30, Especializacao.ESTOFADOR)
        Trab
      case TipoAvaria.CARPETES =>
        val Trab= new Trabalho(x.Tipo_avaria, 25, 28, Especializacao.ESTOFADOR)
        Trab
      case TipoAvaria.OBSERVACAO =>
        val Trab= new Trabalho(x.Tipo_avaria, 0, 0.5, Especializacao.OBSERVACAO)
        Trab
      case TipoAvaria.PRONTO=>
        val Trab = new Trabalho(x.Tipo_avaria, 0, 0.5, Especializacao.OBSERVACAO)
        Trab
    }
  }
}