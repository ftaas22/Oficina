import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.Button
import javafx.stage.{Modality, Stage}

class MainWindowController {

  @FXML
  var mecanicos: Button = _
  @FXML
  var clientes: Button = _
  @FXML
  var gestor: Button = _

  def OnMecanicosClicked(): Unit ={
    val secondStage: Stage = new Stage()
    secondStage.setTitle("Mecânicos")
    secondStage.initModality(Modality.APPLICATION_MODAL)
    secondStage.initOwner(mecanicos.getScene().getWindow)
    val fxmlLoader = new FXMLLoader(getClass.getResource("MecanicosListWindow.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    secondStage.setScene(scene)
    secondStage.show()
  }

  def OnClientsClicked(): Unit ={
    val secondStage: Stage = new Stage()
    secondStage.setTitle("Clientes")
    secondStage.initModality(Modality.APPLICATION_MODAL)
    secondStage.initOwner(clientes.getScene().getWindow)
    val fxmlLoader = new FXMLLoader(getClass.getResource("ClientesWindow.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    secondStage.setScene(scene)
    secondStage.show()
  }

  def OnGestorClicked(): Unit = {
    val secondStage: Stage = new Stage()
    secondStage.initModality(Modality.APPLICATION_MODAL)
    secondStage.initOwner(gestor.getScene().getWindow)
    val fxmlLoader = new FXMLLoader(getClass.getResource("GestorWindow.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    secondStage.setScene(scene)
    secondStage.show()
  }

}
