package dao

import java.sql.{Connection, PreparedStatement}

/**
 * Created by shivangi on 10/30/15.
 */
abstract class Dao {
  val connection = getConnection()
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

}
