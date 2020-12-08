import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import Carro._
import UtilsApp._

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

  def TakeCar(): Unit = {
    CheckIfEmpty(modelo.getText, ano.getText, dono.getText) match{
      case true => {
        labelcarro.setText("Tem de preencher os campos de Modelo, Ano e Dono")
      }
      case false => {

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
        val car = Carro(modelo.getText, ano.getText, dono.getText, trabalho)
        carlist += car
        labelcarro.setText("Modelo: " + modelo.getText + "\nAno: " + ano.getText + "\nDono: " + dono.getText + "\n Adicionado")
      }
    }
  }

  def CheckCar(): Unit = {
    CheckIfEmpty(modelo.getText, ano.getText, dono.getText) match{
      case true => {
        labelcarro.setText("Tem de preencher os campos de Modelo, Ano e Dono")
      }
      case false => {
        val car = FindCar(modelo.getText, ano.getText, dono.getText)
        labelcarro.setText("Modelo: " + modelo.getText + "\nAno: " + ano.getText + "\nDono: " + dono.getText +
          "\n Tipo de Avaria: " + car.trabalho.TipoAvaria + "\n Tempo restante: " + car.trabalho.tempo +
          "\n Pronto: " + car.pronto())
      }
    }
  }

  def CheckIfEmpty(a:String, b:String, c:String) = (a,b,c) match {
    case ("",b,c) => true
    case (a,"",c) => true
    case (a,b,"") => true
    case _ => false
  }

}
