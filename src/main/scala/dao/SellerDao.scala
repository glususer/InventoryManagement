package dao

import java.sql.ResultSet

import vo.Seller

/**
 * Created by shivangi on 11/2/15.
 */
class SellerDao extends Dao{
  private val selectSeller = "SELECT id, name, address FROM Seller where name=?"
  private val createSeller = "INSERT INTO Seller (id, name, address) values (?,?,?)  "

  def insertSellerDetails(Seller: Seller) = {
    try {
      preparedStatement = connection.prepareStatement(createSeller);
      preparedStatement.setString(1, Seller.getId())
      preparedStatement.setString(2, Seller.getName())
      preparedStatement.setString(3, Seller.getAdd())
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

  def getSellerByName(name: String): Option[Seller] = {
    try {
      preparedStatement = connection.prepareStatement(selectSeller)
      preparedStatement.setString(1, name)
      val result: ResultSet = preparedStatement.executeQuery()

      if (result.next()) {
        val Seller = new Seller(result.getString("id"), result.getString("name"), result.getString("address"))
        Some(Seller)
      }
      else None
    } catch {
      case e:Exception => e.getMessage
        throw e
    }
    /*finally {
      cleanUp(connection, preparedStatement)
    }*/
  }

}
