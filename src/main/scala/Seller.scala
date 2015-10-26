
/**
 * Created by shivangi on 10/21/15.
 */
class Seller (name: String, add: String) extends User(name,add ){

  def addItemToInventory(itemName:String, price:Int,qty:Int)= {
    InMemoryManager.addToInventory(itemName,price,qty, this.name)

  }

  def removeItemFromInventory(itemName:String, qty:Int)=InMemoryManager.removeFromInventory(itemName,qty)

}
