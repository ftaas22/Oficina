import java.io.{File, PrintWriter}
import FxApp.meclist
import System._
import javafx.fxml.FXML
import javafx.scene.control.{Label, ListView}

class MecanicosListController {

  @FXML
  var errorLabel: Label = _
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
      errorLabel.setText("Tem de preencher os campos Nome e Especialização")
    }
    case _ => {
      horario.getItems.clear()
      WriteTable(0, mecanicosView.getSelectionModel.getSelectedItem.lista_para_arr)
    }
  }

  def WriteTable(ind: Double, lista: List[Carro]): Unit = lista match {
    case x::xs =>
      if(ind < 8) {
        //trabsemanal.setText("Horas de Trabalho Semanal: " + (ind + x.trabalho.tempo))
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

  def VerCar(): Unit = mecanicosView.getSelectionModel.getSelectedItem match {
    case null => {
      errorLabel.setText("Escolha um mecânico.")
    }
    case _ => {
      val mec = mecanicosView.getSelectionModel.getSelectedItem
      println(mec)
      if(mec.lista_para_arr.length!=0) {
        errorLabel.setText("Modelo: " + mec.lista_para_arr.head.modelo + "\nAno: " + mec.lista_para_arr.head.ano + "\nDono: " + mec.lista_para_arr.head.dono +
          "\n Tipo de Avaria: " + mec.lista_para_arr.head.trabalho.TipoAvaria + "\n Tempo restante: " + mec.lista_para_arr.head.trabalho.tempo +
          "\n Pronto: " + ProntoToString(mec.lista_para_arr.head.pronto()))
      } else errorLabel.setText("O mecânico não tem carros")
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