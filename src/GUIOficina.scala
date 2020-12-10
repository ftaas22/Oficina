import UtilsApp._
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.Try

class GUI extends Application {

  override def start(primaryStage: Stage): Try[Unit] = Try{
    primaryStage.setTitle("GUI")
    val fxmlLoader =
      new FXMLLoader(getClass.getResource("MainWindow.fxml"))
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
    val source: Iterator[String] = Source.fromFile("src\\carros.txt").getLines()
    val source2: Iterator[String] = Source.fromFile("src\\mecanicos.txt").getLines()
    download_Cars(source)
    download_Mec(source2)
    val car1 = Carro("Dacia", "2020", Avaria.defineTrabalho(Avaria(TipoAvaria.MOTOR)), "André")
    val car2 = Carro("Dacia", "2019", Avaria.defineTrabalho(Avaria(TipoAvaria.ALTERNADOR)), "Diogo")
    val car4 = Carro("Mercedes", "1989", Avaria.defineTrabalho(Avaria(TipoAvaria.PINTAR_INTEIOR)), "João")
    val car3 = Carro("Dacia", "2018", Avaria.defineTrabalho(Avaria(TipoAvaria.DIRECAO)), "Vilela")
    val lista: List[Carro] = List(car3, car4, car3, car1, car2, car3, car1, car1)

    val mec = new Mecanico(meclist.head.especializacao, meclist.head.salario, "Vilela", lista)
    meclist += mec
    Application.launch(classOf[GUI], args: _*)
    carListToFile()
    mecListToFile()
  }

}


