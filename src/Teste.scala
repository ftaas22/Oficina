import Avaria.defineTrabalho
import FxApp.{carlist, meclist}
import System.{Trabalhar, adicionarCar_Mec_Final, adicionarCar_Mec_Inicio, atualizarCarros_Mec}
import UtilsApp.{download_Cars, download_Mec}

import scala.io.Source

object Teste {
  def main(args: Array[String]): Unit = {
    val source: Iterator[String] = Source.fromFile("src\\carros2.txt").getLines()
    val source2: Iterator[String] = Source.fromFile("src\\mecanicos3.txt").getLines()
    //download_Cars(source)
    //download_Mec(source2)
    val tra= defineTrabalho(Avaria(TipoAvaria.FUSIVEIS))
    val car = Carro("bmw","2002",tra , "João")
    val car2 = Carro("bmw","2002",tra , "João2")
    val car3 = Carro("bmw","2002",tra , "João3")
    val mec= Mecanico(Especializacao.PINTOR, "20", "Antonio", List(car,car2))
    meclist+=mec
    println(meclist)
    Trabalhar()
    println(meclist)
  }
}
