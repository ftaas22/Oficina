import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}

class ReciboWindowController(mecanico: Mecanico) {

  @FXML
  var contribuinte: TextField = _
  @FXML
  var recibo: Label = _

  def PrintReceipt(): Unit = {
    val temp: String = "Cliente: " + mecanico.lista_para_arr.head.dono + "\nContribuinte" + Contri() + "\nModelo: " + mecanico.lista_para_arr.head.modelo +
      "\nAno: " + mecanico.lista_para_arr.head.ano + "\n Tipo de Avaria: " + mecanico.lista_para_arr.head.trabalho.TipoAvaria +
      "\n Tempo demorado: " + mecanico.lista_para_arr.head.trabalho.tempo
    recibo.setText(temp)

  }

  def Contri(): String = contribuinte.getText match {
    case "" => "999999999"
    case _ => contribuinte.getText
  }

}
