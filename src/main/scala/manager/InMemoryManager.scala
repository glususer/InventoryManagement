package manager

import vo.Item

import scala.collection.mutable.Map
import scala.util.Random

/**
 * Created by shivangi on 10/20/15.
 */
object InMemoryManager extends InventoryManager {

  private object Locker

  var inventoryList: Map[String, Item] = Map().withDefaultValue(null)

  private def createCode(name: String): String = name.concat("_").concat((Random.nextInt(1000)).toString())

  override def addToInventory(itemName: String, price: Int, qty: Int, sellerName: String) = Locker.synchronized{
    if (isQtyForItemAvailable(itemName, qty)) {
      val item = inventoryList(itemName)
      item.setQty(item.getQty() + qty)
      item.addSellerToList(sellerName)
    }
    else {
      val newItem = new Item(itemName, price, createCode(itemName))
      newItem.setQty(qty)
      inventoryList += (itemName -> newItem)
      newItem.addSellerToList(sellerName)
    }
  }

  private def isPresent(itemName: String): Boolean = Locker.synchronized{
    val item = inventoryList.get(itemName)
    item match {
      case Some(item) => true
      case None => false
    }
  }

  override def removeFromInventory(itemName: String, qty: Int) = Locker.synchronized{
      val item = inventoryList(itemName)
      item.setQty(item.getQty() - qty)
  }

  override def addToInventoryAfterCancellation(itemName: String, qty: Int) = {
    if (isPresent(itemName)) {
      val item = inventoryList(itemName)
      item.setQty(item.getQty() + qty)
    }
  }

  override def isQtyForItemAvailable(itemName: String, requiredQty: Int): Boolean = {
    if (isPresent(itemName)) {
      inventoryList(itemName).getQty() >= requiredQty
    } else false
  }

  override def getItem(name: String, requiredQty: Int): Item = {
    val item = inventoryList(name)
    item.setQty(item.getQty() - requiredQty)
    item
  }

  override def isSellerPresentForItem(itemName:String, sellerName:String):Boolean={
    val item = inventoryList(itemName)
    if(item.sellerList.contains(sellerName)) true
    else false
  }
}