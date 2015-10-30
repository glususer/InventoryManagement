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

  var inventoryList:Map[String,Item] = {
    for((key,value) <-InventoryDao.getInventoryList()){
      value.setSellerList(sellerItemDao.getSellerListForItem(key))
    }
    inventoryList
  }

  private def createCode(name: String): String = name.concat("_").concat((Random.nextInt(1000)).toString())

  private def isPresent(itemName: String): Boolean = InventoryDao.isPresent(itemName)

  override def addToInventory(itemName: String, price: Int, qty: Int, sellerName: String) = {
    if (isItemAvailable(itemName, qty)) {
      val item = InventoryDao.getItem(itemName)
      InventoryDao.increaseItemQty(item.getName(),item.getQty() + qty)
      if(sellerItemDao.isNewSeller())
        sellerItemDao.addSeller(sellerName, itemName, item.getCode())
    }
    else {
      val code = createCode(itemName)
      InventoryDao.addToInventory(itemName,code,price,qty)
      sellerItemDao.addSeller(sellerName, itemName, code)
    }
  }

  override def isItemAvailable(name: String, qty: Int): Boolean = InventoryDao.isItemAvailable(name,qty)

  override def addToInventoryAfterCancellation(itemName: String, qty: Int)= {
    if (isPresent(itemName)) {
      val item = InventoryDao.getItem(itemName)
      InventoryDao.increaseItemQty(item.getName(),item.getQty() + qty)
    }
  }

  override def getItem(name: String, requiredQty: Int): Item = {
    InventoryDao.decreaseItemQty(InventoryDao.getQtyForItem(name) - requiredQty)
    val item = InventoryDao.getItem(name)
    item

  }

  override def removeFromInventory(itemName: String, qty: Int) = InventoryDao.removeItem(itemName,qty)
}