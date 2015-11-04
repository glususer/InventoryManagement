/**
 * Created by shivangi on 10/23/15.
 */
class InMemoryManagerSpec extends InMemoryBaseSpec{

  it should "add item to inventory" in {
    selleropA.addItemToInventory("mobile",14000,2)
    selleropB.addItemToInventory("shoes", 300, 56)
    selleropC.addItemToInventory("golf equipment",4000,20)
    selleropC.addItemToInventory("locks", 30, 2896)
  }

  it should " increase quantity of the already added item" in{
    selleropD.addItemToInventory("mobile",14000,2)
    selleropA.addItemToInventory("golf equipment",4000,51)
    selleropB.addItemToInventory("locks", 30, 3000)
  }
  it should "create cart for customer and add items to cart" in {
    customerOperationA.addItemToCart("mobile",1)
    customerOperationA.addItemToCart("locks",10)
    customerOperationA.addItemToCart("golf equipment",3)
    customerOperationA.addItemToCart("mobile",1)
    customerOperationD.addItemToCart("shoes",2)
  }

  it should "Customer A's cart should have 15 items and Customer D's cart should have 2 items" in {
    assert(customerOperationA.noOfItemsInCart()==15)
    assert(customerOperationD.noOfItemsInCart() == 2)
  }
  it should " Not add 3 mobile to customer C's cart as there are only 4 mobile available out of which 2 are in A's cart" in {
    customerOperationC.addItemToCart("mobile",3)
  }


}
