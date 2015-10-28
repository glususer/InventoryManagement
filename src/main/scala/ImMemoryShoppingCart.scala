/**
 * Created by shivangi on 10/16/15.
 */

import scala.collection.mutable.Map

class ImMemoryShoppingCart(id: String) {

  var itemMap: Map[String, Int] = Map()

  def isEmpty(): Boolean = (itemMap.isEmpty)

  def getId() = this.id

  def getItemList() = this.itemMap

  def getNoOfItemsInCart(): Int = {
    var noOfItems = 0
    for ((key, value) <- itemMap)
      noOfItems += value
    noOfItems
  }

  def getItemQty(itemName: String): Int = itemMap(itemName)


  def addToList(itemName: String, qty: Int) = {
    if (InMemoryManager.isItemAvailable(itemName, qty)) {
      val itemQtyAlreadyPresent = itemMap.get(itemName)
      itemQtyAlreadyPresent match {
        case Some(itemQtyAlreadyPresent) => itemMap += ((itemName) -> (qty + itemQtyAlreadyPresent))
        case None => itemMap += ((itemName) -> qty)
      }
      InMemoryManager.getItem(itemName, qty)
    }
    else throw new NoSuchElementException( "Ordered "+ qty + " pieces but only " + InMemoryManager.inventoryList(itemName).getQty() + " pieces are available")
  }

  def removeFromList(itemName: String, qty: Int) = {
    itemMap -= (itemName)
    InMemoryManager.addToInventoryAfterCancellation(itemName, qty)
    this.itemMap -= (itemName)
  }

  override def toString = s"ShoppingCart($getId, $getItemList)"
}
