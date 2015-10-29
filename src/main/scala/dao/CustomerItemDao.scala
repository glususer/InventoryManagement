package dao

import java.sql.{Connection, PreparedStatement}

/**
 * Created by shivangi on 10/28/15.
 */
class CustomerItemDao {

  val increaseItemQty ="UPDATE CustomerItem SET Quantity=? WHERE CustomerName=? AND ItemName=?"
  val insertCustomerItem = "INSERT INTO CustomerItem (CustomerName, ItemName, ItemCode, Quantity) values (?,?,?,?)"
  var preparedStatement: PreparedStatement = null
  val connection:Connection = getConnection()


  def getConnection(): Connection = {
    var connection: Connection = null
    try {
      connection = DBConnection.getConnection

    } catch {
      case e:Exception   => println(e.getMessage)
        throw e
    }
    connection
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

    finally {
      cleanUp(connection, preparedStatement)
    }

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
    finally {
      cleanUp(connection, preparedStatement)
    }
  }
  def cleanUp(connection: Connection, preparedStatement: PreparedStatement) = {
    connection.close()
    preparedStatement.close()
  }

}
