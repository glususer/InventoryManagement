import manager.InMemoryManager
import vo.Seller

/**
 * Created by shivangi on 10/30/15.
 */
class InMemorySellerOperation(seller:Seller) extends  SellerOperation{
  def addItemToInventory(itemName: String, price: Int, qty: Int) = {
    InMemoryManager.addToInventory(itemName, price, qty, seller.getName())

  }

  def removeItemFromInventory(itemName: String, qty: Int) = {
    if(InMemoryManager.isQtyForItemAvailable(itemName,qty) && InMemoryManager.isSellerPresentForItem(itemName,seller.getName()))
      InMemoryManager.removeFromInventory(itemName, qty)
  }
}
