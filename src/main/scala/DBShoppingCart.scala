import dao.CustomerItemDao
import manager.DBManager
import vo.Customer

import scala.collection.mutable.Map
import scala.util.Random

/**
 * Created by shivangi on 10/28/15.
 */
class DBShoppingCart(id: String, customer:Customer) extends ShoppingCart{
  private val customerItemDao = new CustomerItemDao()

  private var itemMap: Map[String, Int] = Map()

  override def isEmpty(): Boolean = (itemMap.isEmpty)

  override def getId= this.id

  override def getItemList() = this.itemMap

  override def getNoOfItemsInCart(): Int = {
    var noOfItems = 0
    for ((key, value) <- itemMap)
      noOfItems += value
    noOfItems
  }

  override def getItemQty(itemName: String): Int = itemMap(itemName)


  override def addToList(itemName: String, qty: Int) = {
    if (DBManager.isItemAvailable(itemName, qty)) {
      val itemQtyAlreadyPresent = itemMap.get(itemName)
      itemQtyAlreadyPresent match {
        case Some(itemQtyAlreadyPresent) => {
          itemMap += ((itemName) -> (qty + itemQtyAlreadyPresent))
          customerItemDao.increaseQty(customer.getName(), itemName, qty + itemQtyAlreadyPresent)
        }
        case None => {
          itemMap += ((itemName) -> qty)
          customerItemDao.addItem(customer.getName(), itemName.concat("_").concat((Random.nextInt(1000)).toString()),itemName, qty)
        }
      }
      DBManager.getItem(itemName, qty)
    }
    else throw new NoSuchElementException( "Ordered "+ qty + " pieces but only " + DBManager.inventoryList(itemName).getQty() + " pieces are available")
  }

  override def removeFromList(itemName: String, qty: Int) = {
    itemMap -= (itemName)
    DBManager.addToInventoryAfterCancellation(itemName, qty)
  }

  override def toString = s"ShoppingCart($getId, $getItemList)"

}
