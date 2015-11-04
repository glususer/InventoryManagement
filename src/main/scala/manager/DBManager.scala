package manager

import dao.{SellerItemDao, InventoryDao}
import vo.Item

import scala.collection.mutable
import scala.collection.mutable.Map
import scala.util.Random

/**
 * Created by shivangi on 10/20/15.
 */

object DBManager extends InventoryManager {

  val sellerItemDao = new SellerItemDao

  var inventoryList: Map[String, Item] = getInventoryList()

  private def getInventoryList(): Map[String, Item] = {
    inventoryList = InventoryDao.getInventoryList()
    for ((key, value) <- inventoryList) {
      value.setSellerList(sellerItemDao.getSellerListForItem(key))
    }
    inventoryList
  }

  private def createCode(name: String): String = name.concat("_").concat((Random.nextInt(1000)).toString())

  private def isPresent(itemName: String): Boolean = InventoryDao.isPresent(itemName)

  override def addToInventory(itemName: String, price: Int, qty: Int, sellerName: String) = {
    if (InventoryDao.isPresent(itemName)) {
      val item = InventoryDao.getItem(itemName)
      InventoryDao.updateItemQty(itemName, item.getQty() + qty)
      if (!sellerItemDao.isSellerPresentForItem(sellerName, itemName))
        sellerItemDao.addSeller(sellerName, itemName, item.getCode(), qty)
    }
    else {
      val code = createCode(itemName)
      InventoryDao.addToInventory(itemName, code, price, qty)
      sellerItemDao.addSeller(sellerName, itemName, code, qty)
    }
  }

  override def isQtyForItemAvailable(name: String, qty: Int): Boolean = InventoryDao.isQtyForItemAvailable(name, qty)

  override def addToInventoryAfterCancellation(itemName: String, qty: Int) = {
    if (isPresent(itemName)) {
      val item = InventoryDao.getItem(itemName)
      InventoryDao.updateItemQty(item.getName(), item.getQty() + qty)
    }
  }

  override def getItem(name: String, requiredQty: Int): Item = {
    InventoryDao.updateItemQty(name, InventoryDao.getItem(name).getQty() - requiredQty)
    val item = InventoryDao.getItem(name)
    item
  }

  override def removeFromInventory(itemName: String, qty: Int) = {
    val previousQty = InventoryDao.getItem(itemName).getQty()
    InventoryDao.updateItemQty(itemName, previousQty - qty)
  }

  override def isSellerPresentForItem(itemName: String, sellerName: String): Boolean = {
    sellerItemDao.isSellerPresentForItem(itemName, sellerName)
  }
}