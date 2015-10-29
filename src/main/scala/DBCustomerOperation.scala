/**
 * Created by shivangi on 10/26/15.
 */

import java.sql.{SQLException, ResultSet, PreparedStatement, Connection}

import dao.CustomerDao
import manager.DBManager
import vo.Customer

import scala.util.Random
import scala.util.control.Exception
import collection.JavaConversions._



class DBCustomerOperation(customer:Customer) extends CustomerOperation {

  private val customerDao = new CustomerDao
  private val shoppingCart = new DBShoppingCart((Random.nextInt(100)).toString().concat(customer.getName()),customer)
  customerDao.insertCustomerDetails(customer)

  override def addItemToCart(itemName: String, qty: Int) = shoppingCart.addToList(itemName, qty)

  override def deleteItemFromCart(itemName: String, qty: Int) = {
    val availableQty = shoppingCart.getItemQty(itemName)
    if (qty <= availableQty)
      shoppingCart.removeFromList(itemName, qty)
    else throw new NoSuchElementException("Only" + availableQty + "items can be deleted")
  }

  override def viewMenu() = {
    for ((key, value) <- DBManager.inventoryList) {
      println(key, value.toString)
    }
  }

  override def viewCart() = {
    if (shoppingCart.isEmpty())
      print(customer.getName() + "'s cart is empty")
    else {
      print(customer.getName() + "'s cart contains the following items")
      for ((key, value) <- shoppingCart.getItemList())
        println(key, value)
    }
  }

  override def noOfItemsInCart(): Int = shoppingCart.getNoOfItemsInCart()
}
