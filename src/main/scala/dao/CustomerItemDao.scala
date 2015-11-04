package dao

import java.sql.{ResultSet, Connection, PreparedStatement}

import scala.collection.mutable
import scala.collection.mutable.Map

/**
 * Created by shivangi on 10/28/15.
 */
class CustomerItemDao extends Dao{
  private val getQtyForItem="SELECT QUANTITY FROM CustomerItem WHERE CustomerName=? AND ItemName=?"
  private val increaseItemQty ="UPDATE CustomerItem SET Quantity=? WHERE CustomerName=? AND ItemName=?"
  private val insertCustomerItem = "INSERT INTO CustomerItem (CustomerName, ItemName, ItemCode, Quantity) values (?,?,?,?)"
  private val getItemsForByCustomerName ="SELECT ItemName , Quantity FROM CustomerItem WHERE CustomerName=?"

  def getQtyForItem(customerName: String, itemName: String):Int = {
    try {
      preparedStatement = connection.prepareStatement(getQtyForItem)
      preparedStatement.setString(1, customerName)
      preparedStatement.setString(2, itemName)

      val resultSet:ResultSet=  preparedStatement.executeQuery()
      while (resultSet.next()) {
        val qty = resultSet.getInt("Quantity")
        return qty
      }
      0
    }
    catch {
      case e:Exception => println(e.getMessage)
        throw e
    }
  }

  def increaseQty(customerName:String, itemName:String, updatedQty:Int) = {
    try {
      preparedStatement = connection.prepareStatement(increaseItemQty);
      preparedStatement.setInt(1, updatedQty)
      preparedStatement.setString(2, customerName)
      preparedStatement.setString(3, itemName)
      preparedStatement.executeUpdate();
    }
    catch {
      case e:Exception => println(e.getMessage)
        throw e
    }
/*
    finally {
      cleanUp(connection, preparedStatement)
    }*/

  }
  def addItem(customerName:String,itemCode:String, itemName:String, qty:Int) = {
    var preparedStatement: PreparedStatement = null
    try {
      preparedStatement = connection.prepareStatement(insertCustomerItem);
      preparedStatement.setString(1, customerName)
      preparedStatement.setString(2, itemName)
      preparedStatement.setString(3, itemCode)
      preparedStatement.setInt(4, qty)

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

  def getItemsForCustomer(customerName: String):Map[String, Int] = {
    try {
      preparedStatement = connection.prepareStatement(getItemsForByCustomerName)
      preparedStatement.setString(1, customerName)
      val resultSet:ResultSet=  preparedStatement.executeQuery()
      var itemMap: Map[String, Int]= Map()
      while (resultSet.next()) {
        itemMap += resultSet.getString("ItemName")->resultSet.getInt("Quantity")
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
}
