import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.{Button, Label, TextField}
import UtilsApp._
import javafx.scene.{Parent, Scene}
import javafx.stage.{Modality, Stage}
import FxApp._

import scala.collection.mutable.ListBuffer

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

  def TakeCar(): Unit = {
    CheckIfEmpty(modelo.getText, ano.getText, dono.getText) match{
      case true => {
        labelcarro.setText("Tem de preencher os campos de Modelo, Ano e Dono")
      }
      case false => {
        val tempcar = FindCar(modelo.getText, ano.getText, dono.getText, carlist.toList)
        val templist = carlist.filterNot(_ == tempcar)
        carlist =  ListBuffer(tempcar)
        val secondStage: Stage = new Stage()
        secondStage.initModality(Modality.APPLICATION_MODAL)
        secondStage.initOwner(takecar.getScene().getWindow)
        val fxmlLoader = new FXMLLoader(getClass.getResource("ReciboWindow.fxml"))
        val mainViewRoot: Parent = fxmlLoader.load()
        val scene = new Scene(mainViewRoot)
        secondStage.setScene(scene)
        secondStage.showAndWait()
        println("Teste")
        carlist = templist
        //RemoveCarIfReady(FindCar(modelo.getText, ano.getText, dono.getText, carlist.toList))
      }
    }
  }

  def RemoveCarIfReady(car: Carro): Unit = {
    car.trabalho.tempo match {
      case 0 => {
        carlist = carlist.filterNot(_ == car)
        labelpronto.setText("Recolheu o carro, obrigado pela confiança! Aqui está o seu recibo.")
      }
      case _ => {
        labelpronto.setText("O Carro ainda não está pronto.")
      }
    }
  }

  def AddCar(): Unit = {
    CheckIfEmpty(modelo.getText, ano.getText, dono.getText) match{
      case true => {
        labelcarro.setText("Tem de preencher os campos de Modelo, Ano e Dono")
      }
      case false => {
        val avaria = Avaria(TipoAvaria.OBSERVACAO)
        val trabalho: Trabalho = Avaria.defineTrabalho(avaria)
        val car = Carro(modelo.getText, ano.getText, trabalho, dono.getText)
        carlist += car
        labelcarro.setText("Modelo: " + modelo.getText + "\nAno: " + ano.getText + "\nDono: " + dono.getText + "\nAdicionado")
      }
    }
  }

  def CheckCar(): Unit = {
    CheckIfEmpty(modelo.getText, ano.getText, dono.getText) match{
      case true => {
        labelcarro.setText("Tem de preencher os campos de Modelo, Ano e Dono")
      }
      case false => {
        val car = FindCarInMec(modelo.getText, ano.getText, dono.getText, meclist.toList)
        if (car != null) {
        labelcarro.setText("Modelo: " + modelo.getText + "\nAno: " + ano.getText + "\nDono: " + dono.getText +
          "\n Tipo de Avaria: " + car.trabalho.TipoAvaria + "\n Tempo restante: " + car.trabalho.tempo +
          "\n Pronto: " + ProntoToString(car.pronto()))
        } else {labelcarro.setText("Não existe esse carro!")}
      }
    }
    //paraOFrancisco
  }

  def ProntoToString(pronto: Boolean): String = pronto match {
    case true => {
      "Sim"
    }
    case false => {
      "Não"
    }
  }

  def CheckIfEmpty(a:String, b:String, c:String) = (a,b,c) match {
    case ("",b,c) => true
    case (a,"",c) => true
    case (a,b,"") => true
    case _ => false
  }

}
