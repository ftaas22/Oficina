import Avaria._

import scala.io.Source
import scala.util.control.Breaks.break

object Teste{
  def main(args: Array[String]) {
    var source:Iterator[String] = Source.fromFile("src\\carros.txt").getLines()
   // var list:Array[Carro]

    /*def find_Avaria( text:String):Avaria = {
      var a = Avaria.values.iterator
      for (z <- a) {
        if (z.toString.contains(text.toUpperCase)) {
          print(z)
          return z
        }
      }

    }*/

    var carlist= List[Carro]()
    for(i<-source){
      //println(i.split(" ")(0))
      //println(i.split(" ")(1))
      //println(i.split(" ")(2))
      //var tra:Trabalho=new Trabalho(Avaria.BATERIA )
      //print(Avaria.withName("BATERIA"))
      var car: Carro = new Carro(i.split(" ")(0),i.split(" ")(1),new Trabalho (Avaria.withName(i.split(" ")(2))))
      carlist::=car
    }

    print(carlist)

  }


}