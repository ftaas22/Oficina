import java.io.{File, PrintWriter}
import FxApp.meclist
import System._
import UtilsApp._
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.control.{Label, ListView, TableColumn, TableView, TextField}

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

  /*def AddMec(): Unit = {
    if (CheckIfAnyEmpty(nome.getText, especializacao.getText, salario.getText)) {
      mecanicos.setText("Tem de preencher todos os campos.")
    } else {
      var mec = Mecanico(Especializacao.withName(especializacao.getText()), salario.getText, nome.getText, null)
      meclist += mec
      mecanicos.setText("Mecânico Adicionado:\n" + nome.getText + "\n" + especializacao.getText + "\n" + salario.getText)
    }
  }

  def RemMec(): Unit = {
    if (CheckIfTwoFirstEmpty(nome.getText, especializacao.getText)) {
      mecanicos.setText("Tem de preencher os campos Nome e Especialização")
    } else {
      meclist = meclist.filterNot(_ == FindMec(nome.getText, Especializacao.withName(especializacao.getText())))
      mecanicos.setText("Removido com sucesso!")
    }
  }

  def CheckIfAnyEmpty(a:String, b:String, c:String): Boolean = (a,b,c) match {
    case ("", _, _) => true
    case (_,"", _) => true
    case (_, _,"") => true
    case _ => false
  }

  def CheckIfTwoFirstEmpty(a:String, b:String): Boolean = (a,b) match {
    case ("", _) => true
    case (_,"") => true
    case _ => false
  }*/

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
      if(mec.lista_para_arr.head!=null) {
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

  }

}