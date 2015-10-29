/**
 * Created by shivangi on 10/29/15.
 */
trait CustomerOperation {

  def addItemToCart(itemName: String, qty: Int)

  def deleteItemFromCart(itemName: String, qty: Int)

  def viewMenu()

  def viewCart()

  def noOfItemsInCart():Int

}
