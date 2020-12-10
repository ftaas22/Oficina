import FxApp._
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import java.io.{File, PrintWriter}

import scala.util.Try

class ReciboWindowController() {

  @FXML
  var contribuinte: TextField = _
  @FXML
  var recibo: Label = _

  def Receipt(): Try[Unit] = Try{
    val tempcar:Carro = carlist.head
    val avaria = Avaria(tempcar.trabalho.TipoAvaria)
    val temptrabalho = Avaria.defineTrabalho(avaria)
    val temp: String = "Cliente: " + tempcar.dono + "\nContribuinte: " + Contri() + "\nModelo: " + tempcar.modelo +
      "\nAno: " + tempcar.ano + "\nTipo de Avaria: " + tempcar.trabalho.TipoAvaria + "\nTempo demorado: " + temptrabalho.tempo
    recibo.setText(temp)
    val writer = new PrintWriter(new File("src\\Recibos\\Recibo " + tempcar.dono + ".txt"))
    writer.write(temp)
    writer.close()
  }

  def Contri(): String = contribuinte.getText match {
    case "" => "999999999"
    case _ => contribuinte.getText
  }

}
