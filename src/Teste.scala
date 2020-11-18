import Avaria._

import scala.io.Source
import scala.util.control.Breaks.break

object Teste{
  def main(args: Array[String]) {
    var source:Iterator[String] = Source.fromFile("src\\carros.txt").getLines()
   // var list:Array[Carro]
    /*var str:String = "ESCAPE"
    def find_Avaria( text:String): Unit = {
      var a = Avaria.values.iterator
      for (z <- a) {
        if (z.toString.contains(text.toUpperCase)) {
          print(z)
          return z
        }
      }

    }

    for(i<-source){
      //println(i.split(" ")(0))
      //println(i.split(" ")(1))
      //println(i.split(" ")(2))
      var tra:Trabalho=new Trabalho(Avaria.BATERIA )

      //var car: Carro = new Carro(i.split(" ")(0),i.split(" ")(1),)
    }*/

    var tra = new Trabalho(Avaria.ESCAPE)
    tra.defineTrabalho()
    print(tra.getEspecializacao())

  }


}