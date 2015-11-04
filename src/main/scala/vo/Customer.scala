package vo

import scala.util.Random

/**
 * Created by shivangi on 10/28/15.
 */
class Customer(name:String, address:String ) {
  var id:String = (Random.nextInt(1000)).toString().concat(name).concat((Random.nextInt(1000)).toString())

  def this(name:String, address:String, id:String){
    this(name,address)
    this.id = id
  }

  def getId() = this.id

  def getName() = this.name

  def getAdd() = this.address

}
