package databases

import models.{BillerDetails, UserAccountDetails}

import scala.collection.mutable.ListBuffer

object DatabaseHandler {

  val userList = new ListBuffer[UserAccountDetails]()

  val billerList = new ListBuffer[BillerDetails]()
}
