import Avaria._
import Especializacao.Especializacao

import scala.collection.convert.ImplicitConversions.`collection asJava`
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

    //adicionar carros a uma lista
    var carlist= List[Carro]()
    for(i<-source){
      var car: Carro = new Carro(i.split(" ")(0),i.split(" ")(1),new Trabalho (Avaria.withName(i.split(" ")(2))), false)
      carlist::=car
    }

    //adicionar mecânicos a uma lista
    var meclist = List[Mecanico]()
    for(i<-source2){
      var mecanico: Mecanico = new Mecanico(Especializacao.withName(i.split(" ")(0)), i.split(" ")(1), null)
      meclist::=mecanico
    }

    //criar o sistema que faz correr os dias
    var system = new System(meclist, carlist)

    system.passarDias()
    //prints teste
    print(carlist)
    print(meclist)

  }
}