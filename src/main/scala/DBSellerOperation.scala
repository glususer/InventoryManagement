import vo.Seller

/**
 * Created by shivangi on 10/30/15.
 */
class DBSellerOperation(seller:Seller) extends SellerOperation{
  override def addItemToInventory(itemName: String, price: Int, qty: Int) = ???

  override def removeItemFromInventory(itemName: String, qty: Int): Unit = ???
}
