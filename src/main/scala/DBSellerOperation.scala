import dao.SellerDao
import manager.DBManager
import vo.Seller

/**
 * Created by shivangi on 10/30/15.
 */
class DBSellerOperation(seller:Seller) extends SellerOperation{
  val sellerDao = new SellerDao()
  sellerDao.insertSellerDetails(seller)

  override def addItemToInventory(itemName: String, price: Int, qty: Int) = DBManager.addToInventory(itemName,price,qty,seller.getName())

  override def removeItemFromInventory(itemName: String, qty: Int)= {
    if(DBManager.isQtyForItemAvailable(itemName,qty) && DBManager.isSellerPresentForItem(itemName,seller.getName()))
      DBManager.removeFromInventory(itemName,qty)
  }
}
