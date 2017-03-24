package actors

import akka.actor.{Actor, ActorLogging}
import databases.DatabaseHandler
import models._


class DatabaseActor extends Actor with ActorLogging {
  override def receive: Receive = {

    case userDetails: UserAccountDetails => {
      if (!DatabaseHandler.userList.exists(user => user.userName == userDetails.userName)) {
        DatabaseHandler.userList += userDetails
      }
      else {
        throw new Exception
      }
    }
    case billerDetails: BillerDetails => DatabaseHandler.billerList += billerDetails


    case (accountNumber: Int, salary: Long, userName: String) => {
      val filteredUser = DatabaseHandler.userList.filter(user => (user.accountNumber == accountNumber)).head

      val newUser = filteredUser.copy(initialAmount = filteredUser.initialAmount + salary)
      DatabaseHandler.userList -= filteredUser
      DatabaseHandler.userList += newUser
      sender() tell(Response(accountNumber), self)
    }

    case (response: Response, category: Category) => {
      val categorywiseBillers = DatabaseHandler.billerList.filter(biller => ((biller.accountNumber == response.accountNumber) && (biller.billerCategory == category)))
      val oldBiller = categorywiseBillers.head
      if (oldBiller.executedIterations <= oldBiller.totalIterations) {

        val amountToBePaid = oldBiller.amount

        val oldUser = DatabaseHandler.userList.filter(user => (user.accountNumber == response.accountNumber)).head
        if(amountToBePaid<=oldUser.initialAmount) {
          val updatedUser = oldUser.copy(initialAmount = oldUser.initialAmount - amountToBePaid)
          DatabaseHandler.userList -= oldUser
          DatabaseHandler.userList += updatedUser

          val newBiller = oldBiller.copy(executedIterations = oldBiller.executedIterations + 1, paidAmount = oldBiller.paidAmount + amountToBePaid)
          DatabaseHandler.billerList -= oldBiller
          DatabaseHandler.billerList += newBiller
        }
        else {
          log.error("Your Amount Balance is Not Sufficient to Pay Your Bills.!!!! ")
        }
      }

      else {
        log.error("Your Installments Have Ended ..!! Thank You.!!!!")
      }
    }


    case DataFetch => {

      sender() ! (DatabaseHandler.userList,DatabaseHandler.billerList)
    }

  }
}