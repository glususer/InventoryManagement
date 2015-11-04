import org.scalatest.{DoNotDiscover, Matchers, FlatSpec}
import vo.{Customer, Seller}

/**
 * Created by shivangi on 11/3/15.
 */
@DoNotDiscover
class DBBaseSpec extends FlatSpec with Matchers{
  val selleropA = new DBSellerOperation(new Seller("sellerA", "29C, paharGanj, New Delhi"))
  val selleropB = new DBSellerOperation(new Seller("sellerB", "Plot number 20/45, Malad West, Mumbai"))
  val selleropC = new DBSellerOperation(new Seller("sellerC", "456 ville parle"))
  val selleropD = new DBSellerOperation(new Seller("sellerD", "12-14H BANJARA HILLS, Hyderabad"))
  val selleropE = new DBSellerOperation(new Seller("sellerE", "14B, Chowranghee lane, Kolkatta"))
  val selleropF = new DBSellerOperation(new Seller("sellerF", "29B/41C ville parle"))

  val customerOperationA = new DBCustomerOperation(new Customer("customerA", "123, downtown"))
  val customerOperationB = new DBCustomerOperation(new Customer("customerB", "456 new jersey"))
  val customerOperationC = new DBCustomerOperation(new Customer("customerC", "19/876, Andheri east"))
  val customerOperationD = new DBCustomerOperation(new Customer("customerD", "41B,  "))

}
