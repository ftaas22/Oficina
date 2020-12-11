import java.io.{File, PrintWriter}
import FxApp.meclist
import System._
import UtilsApp._
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.control.{Label, TableColumn, TableView, TextField}

class MecanicosListController {

  @FXML
  var mecanicos: Label = _
  @FXML
  var nome: TextField = _
  @FXML
  var especializacao: TextField = _
  @FXML
  var salario: TextField = _
  @FXML
  var cars: Label = _
  @FXML
  var carroatual: Label = _
  @FXML
  var trabsemanal: Label = _
  @FXML
  var tv1: TableView[Carro] = _
  @FXML
  var car: TableColumn[String, String] = _
  @FXML
  var ano: TableColumn[String, String] = _
  @FXML
  var dono: TableColumn[String, String] = _

  def UpdateMec(): Unit = {
    mecanicos.setText("")
    val temp: List[Mecanico] = meclist.toList
    PrintMecs(temp)
  }

  def PrintMecs(list:List[Mecanico]): Unit = list match {
    case Nil =>
    case x :: xs => mecanicos.setText(mecanicos.getText + x.nome + " " + x.especializacao + "\n") :: PrintMecs(xs) :: Nil
    case x :: Nil => mecanicos.setText(mecanicos.getText + x.nome + " " + x.especializacao + "\n")
  }

  def AddMec(): Unit = {
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
  }

  def EscolherMecanico(): Unit = {
    if (CheckIfTwoFirstEmpty(nome.getText, especializacao.getText)) {
      mecanicos.setText("Tem de preencher os campos Nome e Especialização")
    } else {
      cars.setText("\n")
      WriteTableView()
    }
  }

  def WriteTable(ind: Double, lista: List[Carro]): ObservableList[Carro] = lista match {
    case x::xs =>
      if(ind <= 8) {
        cars.setText(cars.getText + "Modelo: " + x.modelo + ", Ano: " + x.ano + ", Dono: " + x.dono + ", Tempo Restante: " + x.trabalho.tempo + "\n")
        trabsemanal.setText("Horas de Trabalho Semanal: " + (ind + x.trabalho.tempo))
        /*val temp: ObservableList[Carro] = FXCollections.observableArrayList(x)
        val lista: ObservableList[Carro] = FXCollections.observableArrayList(temp, WriteTable(x.trabalho.tempo + ind, xs).)
        lista*/
      }
    /*case x:: Nil =>
      cars.setText(cars.getText + "Modelo: " + x.modelo + ", Ano: " + x.ano + ", Dono: " + x.dono + ", Tempo Restante: " + x.trabalho.tempo + "\n")*/
    case Nil => cars.setText(cars.getText)
  }

  def WriteTableView(): Unit = {
    car.setCellValueFactory(new PropertyValueFactory("modelo"))
    ano.setCellValueFactory(new PropertyValueFactory("ano"))
    dono.setCellValueFactory(new PropertyValueFactory("dono"))
    tv1.setItems(WriteTable(0, FindMec(nome.getText, Especializacao.withName(especializacao.getText())).lista_para_arr))
  }

  def PassarSlot(): Unit = {
    Trabalhar()
  }
  def VerCar(): Unit = {
    if (CheckIfTwoFirstEmpty(nome.getText, especializacao.getText)) {
      mecanicos.setText("Escolha um mecânico.")
    } else {
      val mec = FindMec(nome.getText, Especializacao.withName(especializacao.getText))
      carroatual.setText("Modelo: " + mec.lista_para_arr.head.modelo + "\nAno: " + mec.lista_para_arr.head.ano + "\nDono: " + mec.lista_para_arr.head.dono +
        "\n Tipo de Avaria: " + mec.lista_para_arr.head.trabalho.TipoAvaria + "\n Tempo restante: " + mec.lista_para_arr.head.trabalho.tempo +
        "\n Pronto: " + ProntoToString(mec.lista_para_arr.head.pronto()))
    }
  }

  def ProntoToString(pronto: Boolean): String = if (pronto) {
    "Sim"
  } else {
    "Não"
  }

}