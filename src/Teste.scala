import Avaria._
import Especializacao.Especializacao

import scala.io.Source
import scala.util.control.Breaks.break

object Teste{
  def main(args: Array[String]) {
    var source:Iterator[String] = Source.fromFile("src\\carros.txt").getLines()
    var source2:Iterator[String] = Source.fromFile("src\\mecanicos.txt").getLines()
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

    var mecList = List[Mecanico]()
    for(i<-source2){
      var mecanico: Mecanico = new Mecanico(Especializacao.withName(i.split(" ")(0)), i.split(" ")(1))
      mecList::=mecanico
    }

    print(carlist)
    print(mecList)

  }

//fazer classe que recebo lista dos objetos, ver mecanicos e criar os trabalhos e fazer uma lista de trabalhos

}