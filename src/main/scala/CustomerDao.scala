import java.sql.{ResultSet, PreparedStatement, Connection}

import scala.util.Random

/**
 * Created by shivangi on 10/28/15.
 */
class CustomerDao {


  val connection = getConnection()
  val selectCustomer = "SELECT id, name, address FROM Customer where name=?"
  val createCustomer = "INSERT INTO Customer (id, name, address) values (?,?,?)  "

  def closeConnection() = connection.close()

  def getCustomerIdByName(): String = {
    null
  }


  def getConnection(): Connection = {
    var connection: Connection = null
    try {
      connection = DBConnection.getConnection

    } catch {
      case e => e.getMessage
    }
    connection
  }

  def insertCustomerDetails(customer:Customer) = {
    var preparedStatement: PreparedStatement = null
    try {
      preparedStatement = connection.prepareStatement(createCustomer);
      preparedStatement.setString(1, customer.getId())
      preparedStatement.setString(2, customer.getName())
      preparedStatement.setString(3, customer.getAdd())
      preparedStatement.executeUpdate();
    }
    catch {
      case e => e.getMessage
    }

    finally {
      preparedStatement.close()
      closeConnection
    }
  }

  def getCustomerByName(name: String): Option[Customer] = {
    val preparedStatement: PreparedStatement = connection.prepareStatement(selectCustomer);

    preparedStatement.setString(1, name);
    val result: ResultSet = preparedStatement.executeQuery();
    closeConnection
    if (result.next()) {
      val customer = new Customer(result.getString("id"), result.getString("name"), result.getString("address"))
      Some(customer)
    }
    else None
  }

}
