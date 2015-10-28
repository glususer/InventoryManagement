import scala.collection.mutable
import scala.collection.mutable.Map

/**
 * Created by shivangi on 10/20/15.
 */

object DBManager extends InventoryManager {

  val inventoryListDao = new InventoryListDao()

  private def getInventoryList(): mutable.Map[String, Item] = ???

  var inventoryList: Map[String, Item] = getInventoryList()

  override def addToInventory(itemName: String, price: Int, qty: Int, sellerName: String) = ???

  override def isItemAvailable(name: String, qty: Int): Boolean = ???

  private def isPresent(itemName: String): Boolean = inventoryListDao.isPresent(itemName)

  override def addToInventoryAfterCancellation(itemName: String, qty: Int)= {
    if (isPresent(itemName)) {
      inventoryListDao.decreaseItemQty(inventoryListDao.getQtyForItem(itemName) - qty)
    }
  }

  override def getItem(name: String, requiredQty: Int): Item = {
    inventoryListDao.decreaseItemQty(inventoryListDao.getQtyForItem(name) - requiredQty)
    val item = inventoryListDao.getItem(name)
    item

  }

  override def removeFromInventory(itemName: String, qty: Int) = ???
}

