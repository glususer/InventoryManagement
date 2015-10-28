import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by shivangi on 10/23/15.
 */
class BaseSpec extends FlatSpec with Matchers{

//  val manager:InventoryManager = DBManager.getManager()
  val manager:InventoryManager = InMemoryManager

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

}
