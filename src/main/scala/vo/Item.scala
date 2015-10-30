package vo

import scala.collection.mutable.ListBuffer

/**
 * Created by shivangi on 10/16/15.
 */
class Item(name: String, price: Int, code: String) {

  var qty = 1

  var sellerList = new ListBuffer[String]()

  def getCode() = this.code

  def getPrice() = this.price

  def getName() = this.name

  def getQty() = this.qty

  def setQty(newQty: Int) = qty = newQty

  def addSellerToList(sellerName: String) = sellerList += sellerName

  def setSellerList(list: ListBuffer[String]) = {
    for (seller <- list)
      sellerList += seller
  }

  override def toString = s"vo.Item(code: $getCode, price: $getPrice, name: $getName,quantity: $getQty)"

}

