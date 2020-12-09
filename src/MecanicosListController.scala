import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import UtilsApp._
import System._

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
        cars.setText("Horário Semanal \n")
        WriteTable(0, FindMec(nome.getText, Especializacao.withName(especializacao.getText())).lista_para_arr)
      }
    }
  }

  def WriteTable(ind: Double, lista: List[Carro]): Unit = lista match {
    case x::xs => {
      cars.setText(cars.getText + "Modelo: " + x.modelo + ", Ano: " + x.ano + ", Dono: " + x.dono + ", Tempo Restante: " + x.trabalho.tempo + "\n")
      WriteTable(x.trabalho.tempo + ind, xs)
    }
    case x:: Nil => cars.setText(cars.getText + "Modelo: " + x.modelo + ", Ano: " + x.ano + ", Dono: " + x.dono + ", Tempo Restante: " + x.trabalho.tempo + "\n")
    case Nil => cars.setText(cars.getText)
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
        val car = FindCar(modelo.getText, ano.getText, dono.getText)
        labelcarro.setText("Modelo: " + modelo.getText + "\nAno: " + ano.getText + "\nDono: " + dono.getText +
          "\n Tipo de Avaria: " + car.trabalho.TipoAvaria + "\n Tempo restante: " + car.trabalho.tempo +
          "\n Pronto: " + ProntoToString(car.pronto()))
      }
    }
  }

}
