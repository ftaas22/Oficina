import javafx.beans.property.{SimpleStringProperty, StringProperty}

case class Calendar(seg:String, ter:String, qua:String,qui:String,sex:String) {

  def segProperty: StringProperty = new SimpleStringProperty(this.seg)
  def terProperty: StringProperty = new SimpleStringProperty(this.ter)
  def quaProperty: StringProperty = new SimpleStringProperty(this.qua)
  def quiProperty: StringProperty = new SimpleStringProperty(this.qui)
  def sexProperty: StringProperty = new SimpleStringProperty(this.sex)

}
