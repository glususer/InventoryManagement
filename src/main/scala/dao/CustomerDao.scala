package dao

import java.sql.{Connection, PreparedStatement, ResultSet}

import vo.Customer

/**
 * Created by shivangi on 10/28/15.
 */
class CustomerDao {

  val connection = getConnection()
  val selectCustomer = "SELECT id, name, address FROM vo.Customer where name=?"
  val createCustomer = "INSERT INTO vo.Customer (id, name, address) values (?,?,?)  "
  var preparedStatement: PreparedStatement = null

  def cleanUp(connection: Connection, preparedStatement: PreparedStatement) = {
    connection.close()
    preparedStatement.close()
  }

  def getConnection(): Connection = {
    var connection: Connection = null
    try {
      connection = DBConnection.getConnection

    } catch {
      case e:Exception => {
        println(e.getMessage())
        throw e
      }
    }
    connection
  }

  def insertCustomerDetails(customer: Customer) = {
    try {
      preparedStatement = connection.prepareStatement(createCustomer);
      preparedStatement.setString(1, customer.getId())
      preparedStatement.setString(2, customer.getName())
      preparedStatement.setString(3, customer.getAdd())
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

  def getCustomerByName(name: String): Option[Customer] = {
    try {
      preparedStatement = connection.prepareStatement(selectCustomer)
      preparedStatement.setString(1, name)
      val result: ResultSet = preparedStatement.executeQuery()

      if (result.next()) {
        val customer = new Customer(result.getString("id"), result.getString("name"), result.getString("address"))
        Some(customer)
      }
      else None
    } catch {
      case e:Exception => e.getMessage
        throw e
    }
    finally {
      cleanUp(connection, preparedStatement)
    }
  }

}
