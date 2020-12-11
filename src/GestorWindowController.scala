import FxApp._
import System._
import javafx.fxml.FXML
import UtilsApp.getCarList
import UtilsApp.getMecList
import javafx.scene.control.{Label, ListView, TextField}

class GestorWindowController {

  @FXML
  var carListView: ListView[Carro] = _
  @FXML
  var mecListView: ListView[Mecanico] = _
  @FXML
  var carrosPorArranjarLabel: Label = _
  @FXML
  var carrosPorArranjarListView: ListView[Carro] = _
  @FXML
  var errorLabel: Label = _
  @FXML
  var especializacaoView: TextField = _
  @FXML
  var nome: TextField = _
  @FXML
  var especializacao: TextField = _
  @FXML
  var salario: TextField = _
  @FXML
  var mecanicos: Label = _

  def checkCarList(): Unit ={
    carListView.getItems.clear()
    def addCars(cars : List[Carro]): Unit = cars match {
      case Nil => Nil
      case x::xs => {
        carListView.getItems.add(x)
        addCars(xs)
      }
    }
    addCars(getCarList())
  }

  def checkMecList(): Unit ={
    mecListView.getItems.clear()
    def addMecs(mecs : List[Mecanico]): Unit = mecs match {
      case Nil => Nil
      case x::xs => {
        mecListView.getItems.add(x)
        addMecs(xs)
      }
    }
    addMecs(getMecList())
  }

  def showCarsToFix(): Unit= {
    carrosPorArranjarListView.getItems.clear()
    def addCars(cars: List[Carro]): Unit = cars match {
      case Nil => Nil
      case x :: xs => {
        carrosPorArranjarListView.getItems.add(x)
        addCars(xs)
      }
    }
    if(mecListView.getSelectionModel.getSelectedItem.lista_para_arr!=null){
      addCars(mecListView.getSelectionModel.getSelectedItem.lista_para_arr)
    }
    /*print(carrosPorArranjarListView.getOnMouseClicked.handle(MouseEvent))
    carrosPorArranjarListView.setOnMouseClicked(new EventHandler[MouseEvent]() {
       def handle(event: MouseEvent): Unit = {
        println("clicked on " + carrosPorArranjarListView.getSelectionModel.getSelectedItem)
      }
    })
    carrosPorArranjarListView.getOnMouseClicked(new EventHandler[MouseEvent]() {
      def handle(event: MouseEvent): Unit = {
        println("clicked on " + carrosPorArranjarListView.getSelectionModel.getSelectedItem)
      }
    })*/
  }

  def atribuirEspecializacao(): Unit = especializacaoView.getText match {
    case "" => errorLabel.setText("Tem de escolher uma Especialização")
    case _ => {
      atriEspecializacao(Especializacao.withName(especializacaoView.getText.toUpperCase))
      errorLabel.setText("Concluído")
    }
  }

  def AddCarPriority(): Unit = CheckMecCar() match {
    case false => {
      errorLabel.setText("Tem de selecionar um mecânico e um carro da lista de espera!")
    }
    case true => {
      adicionarCar_Mec_Inicio(mecListView.getSelectionModel.getSelectedItem, carListView.getSelectionModel.getSelectedItem)
      errorLabel.setText("Adicionado com Prioridade")
    }
  }

  def AddCar(): Unit = CheckMecCar() match {
    case false => {
      errorLabel.setText("Tem de selecionar um mecânico e um carro da lista de espera!")
    }
    case true => {
      adicionarCar_Mec_Final(mecListView.getSelectionModel.getSelectedItem, carListView.getSelectionModel.getSelectedItem)
      errorLabel.setText("Adicionado sem Prioridade")
    }
  }

  def CheckMecCar(): Boolean = (mecListView.getSelectionModel.getSelectedItem, carListView.getSelectionModel.getSelectedItem) match {
    case (null, _) => false
    case (_, null) => false
    case (_,_) => true
  }

  def sortMaior(): Unit = mecListView.getSelectionModel.getSelectedItems match {
    case null => errorLabel.setText("Tem de selecionar um mecânico")
    case _ => {
      sortMaisTempo(_)
    }
  }

  def sortMenor(): Unit = mecListView.getSelectionModel.getSelectedItems match {
    case null => errorLabel.setText("Tem de selecionar um mecânico")
    case _ => {
      sortMenosTempo(_)
    }
  }

  def PassarSlot(): Unit = {
    Trabalhar()
  }

  def gerCar(): Unit = {
    atualizarCarros_Mec()
  }

  /*def viewEspecializacao(): Unit = {
    /*val listaEspcecializacoes = Especializacao.values.toList
    def adicionar(esp: List[Especializacao.Value]): Unit = {
      case Nil => Nil
      case x :: xs => {
        especializacaoView.getItems.add(esp.head)
        adicionar(esp.tail)
      }
    }
    adicionar(listaEspcecializacoes)*/
  }*/

  def AddMec(): Unit = {
    if (CheckIfAnyEmpty(nome.getText, especializacao.getText, salario.getText)) {
      mecanicos.setText("Tem de preencher todos os campos.")
    } else {
      var mec = Mecanico(Especializacao.withName(especializacao.getText()), salario.getText, nome.getText, null)
      meclist += mec
      mecanicos.setText("Mecânico Adicionado!")
    }
  }

  def RemMec(): Unit = mecListView.getSelectionModel.getSelectedItems match {
    case null => mecanicos.setText("Tem de selecionar um mecânico")
    case _ => {
      meclist = meclist.filterNot(_ == mecListView.getSelectionModel.getSelectedItems)
      mecanicos.setText("Removido com sucesso!")
    }
  }

  def CheckIfAnyEmpty(a:String, b:String, c:String): Boolean = (a,b,c) match {
    case ("", _, _) => true
    case (_,"", _) => true
    case (_, _,"") => true
    case _ => false
  }
  //para push

}
