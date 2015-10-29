/**
 * Created by shivangi on 10/23/15.
 */
class InventoryManagerSpec extends BaseSpec{

  it should "add item to inventory" in {
    sellerA.addItemToInventory("mobile",14000,2)
    sellerB.addItemToInventory("shoes", 300, 56)
    sellerC.addItemToInventory("golf equipment",4000,20)
    sellerC.addItemToInventory("locks", 30, 2896)
  }

  it should " increase quantity of the already added item" in{
    sellerD.addItemToInventory("mobile",14000,2)
    sellerA.addItemToInventory("golf equipment",4000,51)
    sellerB.addItemToInventory("locks", 30, 3000)
  }
  /*it should "create cart for customer and add items to cart" in {
    customerA.addItemToCart("mobile",1)
    customerA.addItemToCart("locks",10)
    customerA.addItemToCart("golf equipment",3)
    customerA.addItemToCart("mobile",1)
    customerD.addItemToCart("shoes",2)
  }

  it should "vo.Customer A's cart should have 15 items and vo.Customer D's cart should have 2 items" in {
    assert(customerA.noOfItemsInCart()==15)
    assert(customerD.noOfItemsInCart() == 2)
  }
  it should " Not add 3 mobile to customer C's cart as there are only 4 mobile available out of which 2 are in A's cart" in {
    customerC.addItemToCart("mobile",3)
  }*/


}
