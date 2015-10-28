import scala.util.Random

/**
 * Created by shivangi on 10/21/15.
 */
class Seller(name: String, add: String)  {
  var id:String = (Random.nextInt(1000)).toString().concat(name).concat((Random.nextInt(1000)).toString())

  def this (name: String, add: String, id:String)={
    this(name,add)
    this.id = id
  }

  def getName() = this.name

  def getId() = this.id

  def getAdd() = this.add

  def addItemToInventory(itemName: String, price: Int, qty: Int) = {
    InMemoryManager.addToInventory(itemName, price, qty, this.name)

  }

  def removeItemFromInventory(itemName: String, qty: Int) = InMemoryManager.removeFromInventory(itemName, qty)

}
