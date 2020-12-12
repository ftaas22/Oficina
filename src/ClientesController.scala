import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.{Button, Label, TextField}
import UtilsApp._
import javafx.scene.{Parent, Scene}
import javafx.stage.{Modality, Stage}
import FxApp._

import scala.collection.mutable.ListBuffer
import scala.util.Try

class ClientesController {

  @FXML
  var modelo: TextField = _
  @FXML
  var ano: TextField = _
  @FXML
  var dono: TextField = _
  @FXML
  var labelcarro: Label = _
  @FXML
  var labelpronto: Label = _
  @FXML
  var takecar: Button = _
  @FXML
  var avaria: TextField = _

  def TakeCar(): Try[Unit] = Try{
    if (CheckIfEmpty(modelo.getText, ano.getText, dono.getText)) {
      labelcarro.setText("Tem de preencher os campos de Modelo, Ano e Dono")
    } else {
      RemoveCarIfReady(FindInAll(modelo.getText, ano.getText, dono.getText))
    }
  }

  def RemoveCarIfReady(car: Carro): Unit = /*Try[Unit] = Try*/ {
    if (car != null) {
      car.trabalho.tempo match {
        case 0 =>
          labelpronto.setText("Recolheu o carro, obrigado pela confiança!")
          val templist = carlist.filterNot(_ == car)
          carlist = ListBuffer(car)
          val secondStage: Stage = new Stage()
          secondStage.setTitle("Recibo")
          secondStage.initModality(Modality.APPLICATION_MODAL)
          secondStage.initOwner(takecar.getScene.getWindow)
          val fxmlLoader = new FXMLLoader(getClass.getResource("ReciboWindow.fxml"))
          val mainViewRoot: Parent = fxmlLoader.load()
          val scene = new Scene(mainViewRoot)
          secondStage.setScene(scene)
          secondStage.showAndWait()
          println("Teste")
          carlist = templist
        case _ =>
          labelpronto.setText("O Carro ainda não está pronto.")
      }
    } else labelpronto.setText("Não foi possível encontrar este carro!")
  }

  def AddCar(): Try[Unit] = Try{
    if (CheckIfEmpty(modelo.getText, ano.getText, dono.getText)) {
      labelcarro.setText("Tem de preencher os campos de Modelo, Ano e Dono")
    } else {
      if(avaria.getText == "") {
        val ava = Avaria(TipoAvaria.OBSERVACAO)
        val trabalho: Trabalho = Avaria.defineTrabalho(ava)
        val car = Carro(modelo.getText, ano.getText, trabalho, dono.getText)
        carlist += car
      } else {
        val ava = Avaria(TipoAvaria.withName(avaria.getText.toUpperCase()))
        val trabalho: Trabalho = Avaria.defineTrabalho(ava)
        val car = Carro(modelo.getText, ano.getText, trabalho, dono.getText)
        carlist += car
      }
      labelcarro.setText("Modelo: " + modelo.getText + "\nAno: " + ano.getText + "\nDono: " + dono.getText + "\n Avaria: " + avaria.getText + "\nAdicionado")
    }
  }

  def CheckCar():Unit = {
    if (CheckIfEmpty(modelo.getText, ano.getText, dono.getText)) {
      labelcarro.setText("Tem de preencher os campos de Modelo, Ano e Dono")
    } else {
      val car = FindCarInMec(modelo.getText, ano.getText, dono.getText, meclist.toList)
      if (car != null) {
        labelcarro.setText("Modelo: " + modelo.getText + "\nAno: " + ano.getText + "\nDono: " + dono.getText +
          "\n Tipo de Avaria: " + car.trabalho.TipoAvaria + "\n Tempo restante: " + car.trabalho.tempo +
          "\n Pronto: " + ProntoToString(car.pronto()))
      } else {
        val car1 = FindCar(modelo.getText, ano.getText, dono.getText, carlist.toList)
        if (car1 != null) {
          labelcarro.setText("Modelo: " + modelo.getText + "\nAno: " + ano.getText + "\nDono: " + dono.getText +
            "\n Tipo de Avaria: " + car1.trabalho.TipoAvaria + "\n Tempo restante: " + car1.trabalho.tempo +
            "\n Pronto: " + ProntoToString(car1.pronto()))
        } else {
          labelcarro.setText("Não foi possível encontrar este carro!")
        }
      }
    }
  }

  def ProntoToString(pronto: Boolean): String = if (pronto) {
    "Sim"
  } else {
    "Não"
  }

  def CheckIfEmpty(a:String, b:String, c:String): Boolean = (a,b,c) match {
    case ("", _, _) => true
    case (_,"", _) => true
    case (_, _,"") => true
    case _ => false
  }

}
