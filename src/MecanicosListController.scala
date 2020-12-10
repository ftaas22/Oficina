import javafx.fxml.FXML
import javafx.scene.control.{Label, TableColumn, TableView, TextField}
import UtilsApp._
import System._
import FxApp.meclist

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

  def UpdateMec(): Unit = {
    mecanicos.setText("")
    val temp: List[Mecanico] = meclist.toList
    PrintMecs(temp)
  }

  def PrintMecs(list:List[Mecanico]): Unit = list match {
    case Nil => list
    case x :: xs => mecanicos.setText(mecanicos.getText + x.nome + " " + x.especializacao + "\n") :: PrintMecs(xs) :: Nil
    case x :: Nil => mecanicos.setText(mecanicos.getText + x.nome + " " + x.especializacao + "\n")
  }

  def AddMec(): Unit = {
    CheckIfAnyEmpty(nome.getText, especializacao.getText, salario.getText) match {
      case true => {
        mecanicos.setText("Tem de preencher todos os campos.")
      }
      case false => {
        var mec = Mecanico(Especializacao.withName(especializacao.getText()), salario.getText, nome.getText, null)
        meclist += mec
        mecanicos.setText("Mecânico Adicionado:\n" + nome.getText + "\n" + especializacao.getText + "\n" + salario.getText)
      }
    }
  }

  def RemMec(): Unit = {
    CheckIfTwoFirstEmpty(nome.getText, especializacao.getText) match {
      case true => {
        mecanicos.setText("Tem de preencher os campos Nome e Especialização")
      }
      case false => {
        meclist = meclist.filterNot(_ == FindMec(nome.getText, Especializacao.withName(especializacao.getText())))
        mecanicos.setText("Removido com sucesso!")
      }
    }
  }

  def CheckIfAnyEmpty(a:String, b:String, c:String) = (a,b,c) match {
    case ("",b,c) => true
    case (a,"",c) => true
    case (a,b,"") => true
    case _ => false
  }

  def CheckIfTwoFirstEmpty(a:String, b:String) = (a,b) match {
    case ("",b) => true
    case (a,"") => true
    case _ => false
  }

  def EscolherMecanico(): Unit = {
    CheckIfTwoFirstEmpty(nome.getText, especializacao.getText) match {
      case true => {
        mecanicos.setText("Tem de preencher os campos Nome e Especialização")
      }
      case false => {
        cars.setText("\n")
        WriteTable(0, FindMec(nome.getText, Especializacao.withName(especializacao.getText())).lista_para_arr)
      }
    }
  }

  def WriteTable(ind: Double, lista: List[Carro]): Unit = lista match {
    case x::xs => {
      if(ind <= 40) {
        cars.setText(cars.getText + "Modelo: " + x.modelo + ", Ano: " + x.ano + ", Dono: " + x.dono + ", Tempo Restante: " + x.trabalho.tempo + "\n")
        trabsemanal.setText("Horas de Trabalho Semanal: " + ind)
        WriteInTableView(ind, x.trabalho.tempo, x.modelo + x.ano + x.dono)
        WriteTable(x.trabalho.tempo + ind, xs)
      }
    }
    case x:: Nil => {
      cars.setText(cars.getText + "Modelo: " + x.modelo + ", Ano: " + x.ano + ", Dono: " + x.dono + ", Tempo Restante: " + x.trabalho.tempo + "\n")
      WriteInTableView(ind, x.trabalho.tempo, x.modelo + x.ano + x.dono)
    }
    case Nil => cars.setText(cars.getText)
  }

  def WriteInTableView (tempototal: Double, tempoavaria: Double, s: String): Unit = tempototal match {
    case 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 => {
      if(tempoavaria > 0) {
        println("Segunda-feira " + " " + s)
        WriteInTableView(tempototal+1,tempoavaria-1,s)
      }
    }
    case 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 => {
      if (tempoavaria > 0) {
        println("Terça-feira " + tempototal + " " + s)
        WriteInTableView(tempototal + 1, tempoavaria - 1, s)
      }
    }
    case 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 => {
      if (tempoavaria > 0) {
        println("Quarta-feira " + tempototal + " " + s)
        WriteInTableView(tempototal + 1, tempoavaria - 1, s)
      }
    }
    case 24 | 25 | 26 | 27 | 28 | 29 | 30 | 31 => {
      if (tempoavaria > 0) {
        println("Quinta-feira " + tempototal + " " + s)
        WriteInTableView(tempototal + 1, tempoavaria - 1, s)
      }
    }
    case 32 | 33 | 34 | 35 | 36 | 37 | 38 | 39 => {
      if (tempoavaria > 0) {
        println("Sexta-feira " + tempototal + " " + s)
        WriteInTableView(tempototal + 1, tempoavaria - 1, s)
      }
    }
  }


  def PassarSlot(): Unit = {
    Trabalhar()
  }

  def VerCar(): Unit = {
    CheckIfTwoFirstEmpty(nome.getText, especializacao.getText) match {
      case true => {
        mecanicos.setText("Escolha um mecânico.")
      }
      case false => {
        val mec = FindMec(nome.getText, Especializacao.withName(especializacao.getText))
        carroatual.setText("Modelo: " + mec.lista_para_arr.head.modelo + "\nAno: " + mec.lista_para_arr.head.ano + "\nDono: " + mec.lista_para_arr.head.dono +
          "\n Tipo de Avaria: " + mec.lista_para_arr.head.trabalho.TipoAvaria + "\n Tempo restante: " + mec.lista_para_arr.head.trabalho.tempo +
          "\n Pronto: " + ProntoToString(mec.lista_para_arr.head.pronto()))
      }
    }
  }

  def ProntoToString(pronto: Boolean): String = pronto match {
    case true => {
      "Sim"
    }
    case false => {
      "Não"
    }
  }

}