import scala.util.Random

/**
 * Created by shivangi on 10/28/15.
 */
class Customer(name:String, address:String, id:String) {

  def this(name:String, address:String){
    this((Random.nextInt(1000)).toString().concat(name).concat((Random.nextInt(1000)).toString()), name, address)
  }

  def getId() = this.id

  def getName() = this.name

  def getAdd() = this.address

}
