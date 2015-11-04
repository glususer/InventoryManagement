package dao

import java.sql.{ResultSet, Connection, PreparedStatement}

import vo.Item

import scala.collection.mutable
import scala.collection.mutable.Map

/**
 * Created by shivangi on 10/28/15.
 */
object InventoryDao extends  Dao{

  private val updateQtyForItem = "UPDATE InventoryList SET Quantity=? WHERE ItemName=? "
  private val addItemToInventory = "INSERT INTO InventoryList (ItemName, ItemCode, Price,Quantity ) values (?,?,?,?)  "
  private val fetchInventoryList ="SELECT * FROM InventoryList"
  private val isQtyForItemAvailable = "SELECT COUNT(*) FROM InventoryList where ItemName =? AND Quantity >=?"
  private val getItemByItemName="SELECT * FROM InventoryList WHERE itemName=?"
  private val isItemPresent="SELECT COUNT(*) FROM InventoryList WHERE itemName=?"

  def updateItemQty(itemName: String, qty: Int) = {
    try {
      preparedStatement = connection.prepareStatement(updateQtyForItem);
      preparedStatement.setInt(1, qty)
      preparedStatement.setString(2, itemName)
      preparedStatement.executeUpdate();
    }
    catch {
      case e:Exception => println(e.getMessage)
        throw e
    }
    /*finally {
      cleanUp(connection, preparedStatement)
    }*/
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
    /*finally {
      cleanUp(connection, preparedStatement)
    }*/
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

    /*finally {
      cleanUp(connection, preparedStatement)
    }*/

  }

  def isQtyForItemAvailable(name: String, qty: Int): Boolean = {
    try {
      preparedStatement = connection.prepareStatement(isQtyForItemAvailable)
      preparedStatement.setString(1, name)
      preparedStatement.setInt(2, qty)
      val resultSet:ResultSet=  preparedStatement.executeQuery()
      if(resultSet.next()){
        if (resultSet.getInt("COUNT(*)") ==1) true
        else false
      }
      else false

    }
    catch {
      case e:Exception => println(e.getMessage)
        throw e
    }
    /*finally {
      cleanUp(connection, preparedStatement)
    }*/
  }

  def getItem(itemName: String):Item = {
    try {
      preparedStatement = connection.prepareStatement(getItemByItemName)
      preparedStatement.setString(1, itemName)
      val resultSet:ResultSet=  preparedStatement.executeQuery()
      var item:Item = null
      if(resultSet.next()) {
        item = new Item(resultSet.getString("ItemName"),  resultSet.getInt("Price"),  resultSet.getString("ItemCode"))
        item.setQty(resultSet.getInt("Quantity"))
      }
      item
    }
    catch {
      case e:Exception => println(e.getMessage)
        throw e
    }
    /*finally {
      cleanUp(connection, preparedStatement)
    }*/

  }

  def isPresent(itemName: String): Boolean = {
    try {
      preparedStatement = connection.prepareStatement(isItemPresent);
      preparedStatement.setString(1, itemName)
      val resultSet:ResultSet=  preparedStatement.executeQuery();
      if(resultSet.next()){
        if (resultSet.getInt("COUNT(*)") ==1) true
        else false
      }
      else false
    }
    catch {
      case e:Exception => println(e.getMessage)
        throw e
    }
   /* finally {
      cleanUp(connection, preparedStatement)
    }*/
  }
}
