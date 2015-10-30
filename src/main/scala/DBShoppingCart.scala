import dao.CustomerItemDao
import manager.DBManager
import vo.Customer

import scala.collection.mutable
import scala.collection.mutable.Map
import scala.util.Random

/**
 * Created by shivangi on 10/28/15.
 */
class DBShoppingCart(id: String, customer: Customer) extends ShoppingCart {
  private val customerItemDao = new CustomerItemDao()

  private val itemMap: Map[String, Int] = generateItemMap()

  override def isEmpty(): Boolean = (itemMap.isEmpty)

  override def getId = this.id

  override def getItemList() = this.itemMap

  override def getNoOfItemsInCart(): Int = {
    var noOfItems = 0
    for ((key, value) <- getItemList())
      noOfItems += value
    noOfItems
  }

  override def getItemQty(itemName: String): Int = customerItemDao.getQtyForItem(customer.getName(), itemName)


  override def addToList(itemName: String, qty: Int) = {
    if (DBManager.isItemAvailable(itemName, qty)) {
      val itemQtyAlreadyPresent = getItemQty(itemName)
      if (itemQtyAlreadyPresent > 0)
        customerItemDao.increaseQty(customer.getName(), itemName, qty + itemQtyAlreadyPresent)
      else
        customerItemDao.addItem(customer.getName(), itemName.concat("_").concat((Random.nextInt(1000)).toString()), itemName, qty)

      DBManager.getItem(itemName, qty)
    }
    else throw new NoSuchElementException("Ordered " + qty + " pieces but only " + DBManager.inventoryList(itemName).getQty() + " pieces are available")
  }

  override def removeFromList(itemName: String, qty: Int) = {
    DBManager.addToInventoryAfterCancellation(itemName, qty)
  }

  override def toString = s"ShoppingCart($getId, $getItemList)"

  def generateItemMap(): mutable.Map[String, Int] = {
    customerItemDao.getItemsForCustomer(customer.getName())

  }

}
