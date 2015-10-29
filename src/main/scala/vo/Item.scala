package vo

/**
 * Created by shivangi on 10/16/15.
 */
class Item(name:String, price: Int, code:String) {

  var qty = 1

  var sellerList = List()

  def getCode() = this.code

  def getPrice() = this.price

  def getName() = this.name

  def getQty()=this.qty

  def setQty(newQty:Int) = qty=newQty

  def addSellerToList(sellerName:String)= sellerList:::List(sellerName)

  override def toString = s"vo.Item(code: $getCode, price: $getPrice, name: $getName,quantity: $getQty)"

}

