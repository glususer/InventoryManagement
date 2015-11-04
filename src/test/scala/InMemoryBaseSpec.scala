import manager.{InventoryManager, InMemoryManager}
import org.scalatest.{DoNotDiscover, FlatSpec, Matchers}
import vo.{Seller, Customer}

/**
 * Created by shivangi on 10/23/15.
 */
@DoNotDiscover
class InMemoryBaseSpec extends FlatSpec with Matchers{

  val selleropA = new InMemorySellerOperation(new Seller("sellerA", "29C, paharGanj, New Delhi"))
  val selleropB = new InMemorySellerOperation(new Seller("sellerB", "Plot number 20/45, Malad West, Mumbai"))
  val selleropC = new InMemorySellerOperation(new Seller("sellerC", "456 ville parle"))
  val selleropD = new InMemorySellerOperation(new Seller("sellerD", "12-14H BANJARA HILLS, Hyderabad"))
  val selleropE = new InMemorySellerOperation(new Seller("sellerE", "14B, Chowranghee lane, Kolkatta"))
  val selleropF = new InMemorySellerOperation(new Seller("sellerF", "29B/41C ville parle"))

  val customerOperationA = new InMemoryCustomerOperation(new Customer("customerA", "123, downtown"))
  val customerOperationB = new InMemoryCustomerOperation(new Customer("customerB", "456 new jersey"))
  val customerOperationC = new InMemoryCustomerOperation(new Customer("customerC", "19/876, Andheri east"))
  val customerOperationD = new InMemoryCustomerOperation(new Customer("customerD", "41B,  "))

}
