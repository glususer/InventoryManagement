/**
 * Created by shivangi on 10/21/15.
 */

object Main extends App() {
  new run()
}

class run() {

  val customerA = new Customer("customerA", "123, downtown")
  val customerB = new Customer("customerB", "456 new jersey")
  val customerC = new Customer("customerC", "19/876, Andheri east")
  val customerD = new Customer("customerD", "41B,  ")

  val sellerA = new Seller("sellerA", "29C, paharGanj, New Delhi")
  val sellerB = new Seller("sellerB", "Plot number 20/45, Malad West, Mumbai")
  val sellerC = new Seller("sellerC", "456 ville parle")
  val sellerD = new Seller("sellerD", "12-14H BANJARA HILLS, Hyderabad")
  val sellerE = new Seller("sellerE", "14B, Chowranghee lane, Kolkatta")
  val sellerF = new Seller("sellerF", "29B/41C ville parle")

  sellerA.addItemToInventory("mobile", 14000, 2)
  sellerB.addItemToInventory("shoes", 300, 56)
  sellerC.addItemToInventory("golf equipment", 4000, 20)
  sellerC.addItemToInventory("locks", 30, 2896)

  sellerD.addItemToInventory("mobile", 14000, 2)
  sellerA.addItemToInventory("golf equipment", 4000, 51)
  sellerB.addItemToInventory("locks", 30, 3000)

  customerA.viewMenu()
  customerA.addItemToCart("mobile", 1)
  customerA.addItemToCart("locks", 10)
  customerA.addItemToCart("golf equipment", 3)
  customerA.addItemToCart("mobile", 1)
  customerA.viewCart()

  customerD.addItemToCart("shoes", 2)
  customerD.viewCart()

  customerC.addItemToCart("mobile", 3)
  customerC.viewCart()


}
