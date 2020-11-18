

object Avaria extends Enumeration {

  type Avaria = Value
  val BATERIA, FUSIVEIS, VELAS, ALTERNADOR, MOTORES_ELETRICOS, IGNICAO, ALMOGADELAS, ALINHAMENTO_CHASSI, LAVAR, SUSPENSAO, PINTAR_PAINEL, PINTAR_EXTERIOR, PINTAR_INTEIOR, MOTOR, ARCONDICIONADO, TRANSMISSAO, DIRECAO, RODA, RADIADOR, ESCAPE, ESTOFOSFRENTE, ESTOFOSTRAS, ESTOFOSPORTA, ESTOFOSBAGAGEM, TROCA_DE_VIDRO, CARPETES, OBSERVACAO, PRONTO = Value


}
/*import Avaria._
class Avaria_cla(private var avaria: Avaria.Value) {
  def find_Avaria(text: String): Avaria.Value = {
    var a = Avaria.values.iterator
    for (z <- a) {
      if (z.toString.contains(text.toUpperCase)) {
        print(z)
        return z
      }
    }

  }
}*/

