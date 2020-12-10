case class Calendar(name:String, size:Double) {

  def nameProperty: StringProperty = new SimpleStringProperty(this.name)
  def sizeProperty: DoubleProperty = new SimpleDoubleProperty(this.size)

}
