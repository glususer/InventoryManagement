import scala.util.Random

/**
 * Created by shivangi on 10/16/15.
 */
class InMemoryCustomerOperation(customer: Customer)  {

  val shoppingCart = new ImMemoryShoppingCart((Random.nextInt(100)).toString().concat(customer.getName()))

  def addItemToCart(name: String, qty: Int) = shoppingCart.addToList(name, qty)

  def deleteItemFromCart(itemName: String, qty: Int) = {
    val availableQty = shoppingCart.getItemQty(itemName)
    if (qty <= availableQty)
      shoppingCart.removeFromList(itemName, qty)
    else throw new NoSuchElementException("Only"+ availableQty +"items can be deleted")
  }



  def viewMenu() = {
    for ((key, value) <- InMemoryManager.inventoryList) {
      println(key, value.toString)
    }
  }

  def viewCart() = {
    if(shoppingCart.isEmpty())
       print(customer.getName() + "'s cart is empty")
    else {
      print(customer.getName() + "'s cart contains the following items")
      for ((key, value) <- shoppingCart.getItemList())
        println(key, value)
    }
  }

  def getShoppingCart():String = shoppingCart.toString

  def noOfItemsInCart(): Int = shoppingCart.getNoOfItemsInCart()
}
