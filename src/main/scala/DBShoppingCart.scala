import scala.collection.mutable.Map
import scala.util.Random

/**
 * Created by shivangi on 10/28/15.
 */
class DBShoppingCart(id: String) {
  val customerDao = new CustomerDao()
  val customerItemDao = new CustomerItemDao()
  val inventoryListDao = new InventoryListDao()

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
    if (DBManager.isItemAvailable(itemName, qty)) {
      val itemQtyAlreadyPresent = itemMap.get(itemName)
      itemQtyAlreadyPresent match {
        case Some(itemQtyAlreadyPresent) => {
          itemMap += ((itemName) -> (qty + itemQtyAlreadyPresent))
          customerItemDao.increaseQty(customerDao.getCustomerIdByName(), inventoryListDao.getItemCodeByName(itemName), qty + itemQtyAlreadyPresent)
        }
        case None => {
          itemMap += ((itemName) -> qty)
          customerItemDao.addItem(customerDao.getCustomerIdByName(), inventoryListDao.getItemCodeByName(itemName), qty)
        }
      }
      DBManager.getItem(itemName, qty)
    }
    else throw new NoSuchElementException( "Ordered "+ qty + " pieces but only " + DBManager.inventoryList(itemName).getQty() + " pieces are available")
  }

  def removeFromList(itemName: String, qty: Int) = {
    itemMap -= (itemName)
    DBManager.addToInventoryAfterCancellation(itemName, qty)
  }

  override def toString = s"ShoppingCart($getId, $getItemList)"

}
