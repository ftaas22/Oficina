import javafx.fxml.FXML
import UtilsApp.getCarList
import UtilsApp.getMecList
import javafx.event.EventHandler
import javafx.scene.control.{Label, ListView}
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent

class GestorWindowController {

  @FXML
  var carListView: ListView[Carro] = _

  @FXML
  var mecListView: ListView[Mecanico] = _

  @FXML
  var carrosPorArranjarLabel: Label = _

  @FXML
  var carrosPorArranjarListView: ListView[Carro] = _


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

}
