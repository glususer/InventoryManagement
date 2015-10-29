import scala.collection.mutable.Map

/**
 * Created by shivangi on 10/29/15.
 */
trait ShoppingCart {

  def isEmpty(): Boolean

  def getId():String

  def getItemList():Map[String,Int]

  def getNoOfItemsInCart(): Int

  def getItemQty(itemName: String): Int

  def addToList(itemName: String, qty: Int)

  def removeFromList(itemName: String, qty: Int)

}
