import scala.util.Random

/**
 * Created by shivangi on 10/16/15.
 */
class Customer(name: String, add: String) extends User(name,add ) {

  val shoppingCart = new ShoppingCart((Random.nextInt(100)).toString().concat(getName()))

  def addItemToCart(name:String,qty :Int) = shoppingCart.addToList(name,qty)

  def deleteItemFromCart(itemName:String, qty:Int) = {
    val availableQty = shoppingCart.getItemQty(itemName)
    if(qty <= availableQty)
      shoppingCart.removeFromList(itemName,qty)
    else println(getName()," ordered ",qty,"but only ",availableQty,"pieces available.")
  }

  def viewMenu()={
    for ((key,value)<-InMemoryManager.inventoryList){
      println(key, value.toString)
    }
  }

  def viewCart()={
    print(getName()+"'s cart contains the following items")
    for ((key,value) <-shoppingCart.getItemList())
      println(key,value)
  }

  def noOfItemsInCart():Int=shoppingCart.getNoOfItemsInCart()

}
