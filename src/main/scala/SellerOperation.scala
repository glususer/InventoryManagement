/**
 * Created by shivangi on 10/30/15.
 */
trait SellerOperation {
  def addItemToInventory(itemName: String, price: Int, qty: Int)

  def removeItemFromInventory(itemName: String, qty: Int)

}
