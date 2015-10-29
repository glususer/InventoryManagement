package manager

import vo.Item

/**
 * Created by shivangi on 10/17/15.
 */
abstract class InventoryManager {

  def addToInventory(itemName:String, price:Int,  qty:Int, sellerName:String)

  def removeFromInventory(itemName:String, qty:Int)

  def isItemAvailable(name:String, qty:Int):Boolean

  def addToInventoryAfterCancellation(itemName:String, qty:Int)

  def getItem(name:String, requiredQty:Int):Item

}
