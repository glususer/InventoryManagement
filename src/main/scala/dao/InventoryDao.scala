package dao

import java.sql.{ResultSet, Connection, PreparedStatement}

import vo.Item

import scala.collection.mutable
import scala.collection.mutable.Map

/**
 * Created by shivangi on 10/28/15.
 */
object InventoryDao extends  Dao{
  val incrItemQty = "UPDATE InventoryList SET Quantity=? WHERE ItemName=? "
  val addItemToInventory = "INSERT INTO InventoryList (ItemName, ItemCode, Price,Quantity ) values (?,?,?,?)  "
  val fetchInventoryList ="SELECT * FROM InventoryList"

  def removeItem(itemName: String, qty: Int)= {
    try {
      preparedStatement = connection.prepareStatement(incrItemQty);
      preparedStatement.setString(1, itemName)
      preparedStatement.setInt(2, qty)
      preparedStatement.executeUpdate();
    }
    catch {
      case e:Exception => println(e.getMessage)
        throw e
    }
    finally {
      cleanUp(connection, preparedStatement)
    }
  }

  def increaseItemQty(itemName: String, qty: Int) = {
    try {
      preparedStatement = connection.prepareStatement(incrItemQty);
      preparedStatement.setString(1, itemName)
      preparedStatement.setInt(2, qty)
      preparedStatement.executeUpdate();
    }
    catch {
      case e:Exception => println(e.getMessage)
        throw e
    }
    finally {
      cleanUp(connection, preparedStatement)
    }
  }


  def getInventoryList(): Map[String, Item] = {

    try {
      preparedStatement = connection.prepareStatement(fetchInventoryList)
      val resultSet:ResultSet=  preparedStatement.executeQuery()
      var itemMap: Map[String, Item]= Map()
      while (resultSet.next()) {
        val itemName = resultSet.getString("ItemName")
        val item =new Item (itemName,  resultSet.getInt("Price"),resultSet.getString("ItemCode"))
        item.setQty(resultSet.getInt("Quantity"))
        itemMap += itemName->item
      }
      itemMap
    }
    catch {
      case e:Exception => println(e.getMessage)
        throw e
    }
    finally {
      cleanUp(connection, preparedStatement)
    }
  }

  def addToInventory(itemName: String, itemCode:String,price: Int, qty: Int) = {
    try {
      preparedStatement = connection.prepareStatement(addItemToInventory);
      preparedStatement.setString(1, itemName)
      preparedStatement.setString(2, itemCode)
      preparedStatement.setInt(3, price)
      preparedStatement.setInt(4, qty)
      preparedStatement.executeUpdate();
    }
    catch {
      case e:Exception => e.getMessage
        throw e
    }

    finally {
      cleanUp(connection, preparedStatement)
    }

  }

  def isItemAvailable(name: String, qty: Int): Boolean = ???

  def getItem(itemName: String):Item = {
    return null
  }

  def getQtyForItem(itemName: String):Int = {
    0
  }

  def isPresent(itemName: String): Boolean = {
    false
  }

  def decreaseItemQty(value: Int) = {

  }

  // returns itemcode of item from itemName
  def getItemCodeByName(itemName:String): String = {
    null
  }

}
