package dao

import scala.collection.mutable.ListBuffer

/**
 * Created by shivangi on 10/30/15.
 */
class SellerItemDao {
  def getSellerListForItem(key: String):ListBuffer[String] = ???

  def addSeller(sellerName: String, itemName: String, s: String) = ???

  def isNewSeller(): Boolean = ???

}
