package dao

import java.sql.ResultSet

import vo.Item

import scala.collection.mutable.{Map, ListBuffer}

/**
 * Created by shivangi on 10/30/15.
 */
class SellerItemDao extends Dao{

  private val fetchSellerListForItem = "SELECT SellerName FROM SellerItem where ItemName=?"
  private val addSellerForItem = "INSERT INTO SellerItem (SellerName, ItemName, ItemCode,Quantity ) values (?,?,?,?)  "
  private val isSellerNew ="SELECT COUNT(*) FROM SellerItem where SellerName=? and ItemName=?"

  def getSellerListForItem(itemName: String):ListBuffer[String] = {
    try {
      preparedStatement = connection.prepareStatement(fetchSellerListForItem)
      preparedStatement.setString(1, itemName)
      val resultSet:ResultSet=  preparedStatement.executeQuery()
      var itemList = ListBuffer[String]()
      while (resultSet.next()) itemList += resultSet.getString("SellerName")
      itemList
    }
    catch {
      case e:Exception => println(e.getMessage)
        throw e
    }
    /*finally {
      cleanUp(connection, preparedStatement)
    }*/
  }

  def addSeller(sellerName: String, itemName: String, itemCode: String,qty:Int) = {
    try {
      preparedStatement = connection.prepareStatement(addSellerForItem);
      preparedStatement.setString(1, sellerName)
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

  def isSellerPresentForItem(sellerName:String, itemName:String): Boolean = {

    try {
      preparedStatement = connection.prepareStatement(isSellerNew);
      preparedStatement.setString(1, sellerName)
      preparedStatement.setString(2, itemName)
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
    /*finally {
      cleanUp(connection, preparedStatement)
    }*/
  }

}