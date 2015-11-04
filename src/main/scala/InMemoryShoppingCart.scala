/**
 * Created by shivangi on 10/16/15.
 */

import manager.InMemoryManager
import vo.Customer

import scala.collection.mutable.Map

class InMemoryShoppingCart(id: String,customer:Customer) extends ShoppingCart{

  private var itemMap: Map[String, Int] = Map()

  override def isEmpty(): Boolean = (itemMap.isEmpty)

  override def getId():String = this.id

  override def getItemList():Map[String,Int] = this.itemMap

  override def getNoOfItemsInCart(): Int = {
    var noOfItems = 0
    for ((key, value) <- itemMap)
      noOfItems += value
    noOfItems
  }

  override def getItemQty(itemName: String): Int = itemMap(itemName)


  override def addToList(itemName: String, qty: Int) = {
    if (InMemoryManager.isQtyForItemAvailable(itemName, qty)) {
      val itemQtyAlreadyPresent = itemMap.get(itemName)
      itemQtyAlreadyPresent match {
        case Some(itemQtyAlreadyPresent) => itemMap += ((itemName) -> (qty + itemQtyAlreadyPresent))
        case None => itemMap += ((itemName) -> qty)
      }
      InMemoryManager.getItem(itemName, qty)
    }
    else println( "Ordered "+ qty + " pieces but only " + InMemoryManager.inventoryList(itemName).getQty() + " pieces are available")
  }

  override def removeFromList(itemName: String, qty: Int) = {
    itemMap -= (itemName)
    InMemoryManager.addToInventoryAfterCancellation(itemName, qty)
    this.itemMap -= (itemName)
  }

  override def toString = s"ShoppingCart($getId, $getItemList)"
}
