import UtilsApp._
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.collection.mutable.ListBuffer
import scala.io.Source

class GUI extends Application {

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("GUI")
    val fxmlLoader = new FXMLLoader(getClass.getResource("MainWindow.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}

object FxApp {
  var carlist: ListBuffer[Carro] = ListBuffer[Carro]()
  var meclist: ListBuffer[Mecanico] = ListBuffer[Mecanico]()
  def main(args: Array[String]): Unit = {
    val source: Iterator[String] = Source.fromFile("src\\Base De Dados\\carros.txt").getLines()
    val source2: Iterator[String] = Source.fromFile("src\\Base De Dados\\mecanicos.txt").getLines()
    download_Cars(source)
    download_Mec(source2)
    Application.launch(classOf[GUI], args: _*)
    carListToFile()
    mecListToFile()
  }

}


