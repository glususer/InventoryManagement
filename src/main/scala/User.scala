import scala.util.Random

/**
 * Created by shivangi on 10/16/15.
 */
class User(name: String, add: String) {

  val id:String = (Random.nextInt(1000)).toString().concat(name).concat((Random.nextInt(1000)).toString())

  def getName() = this.name

  def getId() = this.id

  def getAdd() = this.add

}
