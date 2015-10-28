/**
 * Created by shivangi on 10/26/15.
 */

import java.sql.{SQLException, ResultSet, PreparedStatement, Connection}

import scala.util.Random
import scala.util.control.Exception

class DBCustomerOperation(customer:Customer) {

  val customerDao = new CustomerDao
  val shoppingCart = new DBShoppingCart((Random.nextInt(100)).toString().concat(customer.getName()))
  customerDao.insertCustomerDetails(customer)

  def addItemToCart(itemName: String, qty: Int) = shoppingCart.addToList(itemName, qty)

  def deleteItemFromCart(itemName: String, qty: Int) = {
    val availableQty = shoppingCart.getItemQty(itemName)
    if (qty <= availableQty)
      shoppingCart.removeFromList(itemName, qty)
    else throw new NoSuchElementException("Only" + availableQty + "items can be deleted")
  }

  def viewMenu() = {
    for ((key, value) <- DBManager.inventoryList) {
      println(key, value.toString)
    }
  }

  def viewCart() = {
    if (shoppingCart.isEmpty())
      print(customer.getName() + "'s cart is empty")
    else {
      print(customer.getName() + "'s cart contains the following items")
      for ((key, value) <- shoppingCart.getItemList())
        println(key, value)
    }
  }

  def noOfItemsInCart(): Int = shoppingCart.getNoOfItemsInCart()
}
