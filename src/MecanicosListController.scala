import java.io.{File, PrintWriter}

import FxApp.meclist
import System._
import UtilsApp._
import javafx.fxml.FXML
import javafx.scene.control.{Label, TableColumn, TableView, TextField}

import scala.util.Try

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
  var tc1: TableColumn[String, String] = _
  @FXML
  var tc2: TableColumn[String, String] = _
  @FXML
  var tc3: TableColumn[String, String] = _
  @FXML
  var tc4: TableColumn[String, String] = _
  @FXML
  var tc5: TableColumn[String, String] = _

  def UpdateMec(): Try[Unit] = Try{
    mecanicos.setText("")
    val temp: List[Mecanico] = meclist.toList
    PrintMecs(temp)
  }

  def PrintMecs(list:List[Mecanico]): Unit = list match {
    case Nil =>
    case x :: xs => mecanicos.setText(mecanicos.getText + x.nome + " " + x.especializacao + "\n") :: PrintMecs(xs) :: Nil
    case x :: Nil => mecanicos.setText(mecanicos.getText + x.nome + " " + x.especializacao + "\n")
  }

  def AddMec(): Try[Unit] = Try{
    if (CheckIfAnyEmpty(nome.getText, especializacao.getText, salario.getText)) {
      mecanicos.setText("Tem de preencher todos os campos.")
    } else {
      var mec = Mecanico(Especializacao.withName(especializacao.getText()), salario.getText, nome.getText, null)
      meclist += mec
      mecanicos.setText("Mecânico Adicionado:\n" + nome.getText + "\n" + especializacao.getText + "\n" + salario.getText)
    }
  }

  def RemMec(): Try[Unit] = Try {
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

  def EscolherMecanico(): Try[Unit] = Try{
    if (CheckIfTwoFirstEmpty(nome.getText, especializacao.getText)) {
      mecanicos.setText("Tem de preencher os campos Nome e Especialização")
    } else {
      cars.setText("\n")
      WriteTable(0, FindMec(nome.getText, Especializacao.withName(especializacao.getText())).lista_para_arr)
    }
  }

  def WriteTable(ind: Double, lista: List[Carro]): Unit = lista match {
    case x::xs =>
      if(ind <= 40) {
        cars.setText(cars.getText + "Modelo: " + x.modelo + ", Ano: " + x.ano + ", Dono: " + x.dono + ", Tempo Restante: " + x.trabalho.tempo + "\n")
        WriteInPrint(ind, x.trabalho.tempo, x.modelo + x.ano + x.dono)
        trabsemanal.setText("Horas de Trabalho Semanal: " + (ind + x.trabalho.tempo))
        WriteTable(x.trabalho.tempo + ind, xs)
      }
    case x:: Nil =>
      cars.setText(cars.getText + "Modelo: " + x.modelo + ", Ano: " + x.ano + ", Dono: " + x.dono + ", Tempo Restante: " + x.trabalho.tempo + "\n")
      WriteInPrint(ind, x.trabalho.tempo, x.modelo + x.ano + x.dono)
    case Nil => cars.setText(cars.getText)
  }

  def WriteInPrint(tempototal: Double, tempoavaria: Double, s: String): Unit = {
    val writer = new PrintWriter(new File("src\\Horario.txt"))
    writer.write("")
    tempototal match {
      case 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 =>
        if (tempoavaria > 0) {
          writer.write("2 " + tempototal + " " + s + "\n")
          WriteInPrint(tempototal + 1, tempoavaria - 1, s)
        }
      case 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 =>
        if (tempoavaria > 0) {
          writer.append("3 " + (tempototal-8) + " " + s + "\n")
          WriteInPrint(tempototal + 1, tempoavaria - 1, s)
        }
      case 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 =>
        if (tempoavaria > 0) {
          writer.append("4 " + (tempototal-16) + " " + s + "\n")
          WriteInPrint(tempototal + 1, tempoavaria - 1, s)
        }
      case 24 | 25 | 26 | 27 | 28 | 29 | 30 | 31 =>
        if (tempoavaria > 0) {
          writer.append("5 " + (tempototal-24) + " " + s + "\n")
          WriteInPrint(tempototal + 1, tempoavaria - 1, s)
        }
      case 32 | 33 | 34 | 35 | 36 | 37 | 38 | 39 =>
        if (tempoavaria > 0) {
          writer.append("6 " + (tempototal-32) + " " + s + "\n")
          WriteInPrint(tempototal + 1, tempoavaria - 1, s)
        }
    }
    writer.close()
  }

  def PassarSlot(): Unit = {
    Trabalhar()
  }
  def VerCar(): Try[Unit] = Try{
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

  def AtualizarHorario(): Unit = {

  }

}