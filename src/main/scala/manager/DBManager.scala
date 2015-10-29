package manager

import dao.InventoryListDao
import vo.Item

import scala.collection.mutable
import scala.collection.mutable.Map

/**
 * Created by shivangi on 10/20/15.
 */

object DBManager extends InventoryManager {

  var inventoryList: Map[String, Item] = getInventoryList()

  private def getInventoryList(): mutable.Map[String, Item] = InventoryListDao.getInventoryList()

  private def isPresent(itemName: String): Boolean = InventoryListDao.isPresent(itemName)

  override def addToInventory(itemName: String, price: Int, qty: Int, sellerName: String) = InventoryListDao.addToInventory(itemName, price, qty, sellerName)

  override def isItemAvailable(name: String, qty: Int): Boolean = InventoryListDao.isItemAvailable(name,qty)

  override def addToInventoryAfterCancellation(itemName: String, qty: Int)= {
    if (isPresent(itemName)) {
      InventoryListDao.decreaseItemQty(InventoryListDao.getQtyForItem(itemName) - qty)
    }
  }

  override def getItem(name: String, requiredQty: Int): Item = {
    InventoryListDao.decreaseItemQty(InventoryListDao.getQtyForItem(name) - requiredQty)
    val item = InventoryListDao.getItem(name)
    item

  }

  override def removeFromInventory(itemName: String, qty: Int) = ???
}