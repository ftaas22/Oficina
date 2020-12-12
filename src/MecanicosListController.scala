import java.io.{File, PrintWriter}
import FxApp.meclist
import System._
import javafx.fxml.FXML
import javafx.scene.control.{Label, ListView}

class MecanicosListController {

  @FXML
  var carroatual: Label = _
  @FXML
  var horario: ListView[Carro] = _
  @FXML
  var mecanicosView: ListView[Mecanico] = _

  def UpdateMec(): Unit = {
    mecanicosView.getItems.clear()
    val temp: List[Mecanico] = meclist.toList
    PrintMecs(temp)
  }

  def PrintMecs(list:List[Mecanico]): Unit = list match {
    case Nil =>
    case x :: xs => mecanicosView.getItems.add(x) :: PrintMecs(xs) :: Nil
    case x :: Nil => mecanicosView.getItems.add(x)
  }

  def EscolherMecanico(): Unit = mecanicosView.getSelectionModel.getSelectedItem match {
    case null => {
      carroatual.setText("É necessário escolher um mecânico.")
    }
    case _ => {
      horario.getItems.clear()
      WriteTable(0, mecanicosView.getSelectionModel.getSelectedItem.lista_para_arr)
    }
  }

  def WriteTable(ind: Double, lista: List[Carro]): Unit = lista match {
    case x::xs =>
      if(ind < 8) {
        horario.getItems.add(x)
        WriteTable(x.trabalho.tempo + ind, xs)
      }
    case x:: Nil =>
      horario.getItems.add(x)
    case Nil =>
  }

  def PassarSlot(): Unit = {
    Trabalhar()
  }

  def VerificarCarroAtual(): Unit = {
    mecanicosView.getSelectionModel.getSelectedItem match {
      case null => {
        carroatual.setText("Escolha um mecânico.")
      }
      case _ => {
        val car = mecanicosView.getSelectionModel.getSelectedItem.lista_para_arr.head
        if (car != null) {
          carroatual.setText("Modelo: " + car.modelo + "\nAno: " + car.ano + "\nDono: " + car.dono +
          "\n Tipo de Avaria: " + car.trabalho.TipoAvaria + "\n Tempo restante: " + car.trabalho.tempo +
          "\n Pronto: " + ProntoToString(car.pronto()))
        } else carroatual.setText("O mecânico não tem carros")
      }
    }
  }

  def ProntoToString(pronto: Boolean): String = if (pronto) {
    "Sim"
  } else {
    "Não"
  }

  def HorasExtra(): Unit = {
    //por preencher
  }

}