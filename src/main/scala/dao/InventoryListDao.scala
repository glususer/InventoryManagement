package dao

import vo.Item

import scala.collection.mutable

/**
 * Created by shivangi on 10/28/15.
 */
object InventoryListDao {

  def getInventoryList(): mutable.Map[String, Item] = ???

  def addToInventory(itemName: String, price: Int, qty: Int, sellerName: String) = ???

  def isItemAvailable(name: String, qty: Int): Boolean = ???

  def getItem(itemName: String):Item = {
    return null
  }

  def getQtyForItem(itemName: String):Int = {
    0
  }

  def isPresent(itemName: String): Boolean = {
    false
  }

  def decreaseItemQty(value: Int) = {

  }

  // returns itemcode of item from itemName
  def getItemCodeByName(itemName:String): String = {
    null
  }

}
