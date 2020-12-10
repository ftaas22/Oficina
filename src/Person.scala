import javafx.beans.property.{DoubleProperty, SimpleDoubleProperty, SimpleStringProperty, StringProperty}

case class Person(name:String, size:Double) {

  def nameProperty: StringProperty = new SimpleStringProperty(this.name)
  def sizeProperty: DoubleProperty = new SimpleDoubleProperty(this.size)

}
