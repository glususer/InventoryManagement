package dao

import java.io.{InputStream, FileInputStream}
import java.sql.{Connection, DriverManager}
import java.util.Properties

/**
 * Created by shivangi on 10/29/15.
 */
object DBConnection {

  def getConnection: Connection = {
    val props: Properties = new Properties
    var con: Connection = null

    try {
      props.load(getClass().getClassLoader().getResourceAsStream("db.properties"))
      Class.forName(props.getProperty("DB_DRIVER_CLASS"))
      con = DriverManager.getConnection(props.getProperty("DB_URL"), props.getProperty("DB_USERNAME"), props.getProperty("DB_PASSWORD"))
    }
    catch {
      case e: Exception => {
        System.out.println(e.getMessage)
        throw e
      }
    }
    return con
  }

}
