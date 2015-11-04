import manager.InMemoryManager
import vo.Customer

import scala.util.Random

/**
 * Created by shivangi on 10/16/15.
 */
class InMemoryCustomerOperation (customer: Customer) extends CustomerOperation {

  val shoppingCart = new InMemoryShoppingCart((Random.nextInt(100)).toString().concat(customer.getName()),customer)

  override def addItemToCart(name: String, qty: Int) = shoppingCart.addToList(name, qty)

  override def deleteItemFromCart(itemName: String, qty: Int) = {
    val availableQty = shoppingCart.getItemQty(itemName)
    if (qty <= availableQty)
      shoppingCart.removeFromList(itemName, qty)
    else println("Only"+ availableQty +"items can be deleted")
  }



  override def viewMenu() = {
    for ((key, value) <- InMemoryManager.inventoryList) {
      println(key, value.toString)
    }
  }

  override def viewCart() = {
    if(shoppingCart.isEmpty())
       print(customer.getName() + "'s cart is empty")
    else {
      print(customer.getName() + "'s cart contains the following items")
      for ((key, value) <- shoppingCart.getItemList())
        println(key, value)
    }
  }

  override def noOfItemsInCart(): Int = shoppingCart.getNoOfItemsInCart()
}
