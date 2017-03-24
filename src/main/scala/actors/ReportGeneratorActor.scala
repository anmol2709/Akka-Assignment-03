package actors

import akka.actor._
import models.{BillerDetails, DataFetch, FetchReport, UserAccountDetails}

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration._

class ReportGeneratorActor(ref: ActorRef) extends Actor with ActorLogging {
  override def receive: Receive = {


    case FetchReport => {

      val system = ActorSystem("AutomatedAccountingSystem")
      import system.dispatcher

      system.scheduler.schedule(0 seconds,
        5 minutes,
        ref,
        (DataFetch))

    }


    case (userList: ListBuffer[UserAccountDetails], billerList: ListBuffer[BillerDetails]) =>
      userList.map {
        user => {
          log.info(" \n \n DETAILS FOR  " + user.nameOfAccountHolder.toUpperCase)
          log.info("UserName : " + user.nameOfAccountHolder)
          log.info("Account Number : " + user.accountNumber)
          log.info("Account Balance : " + user.initialAmount + "\n \n")

          val billersOfUser = billerList.filter(biller => biller.accountNumber == user.accountNumber)
          billersOfUser.map {
            billers => {
              log.info("\n* Biller Details for Category : : :  " + billers.billerCategory)
              log.info("Biller Name : " + billers.billerName)
              log.info("Date of Transaction : " + billers.transactionDate)
              log.info("Total Installments to be Paid : " + billers.totalIterations)
              log.info("Reamaining Installments to be Paid : " + billers.executedIterations)
              log.info("Total Amount Paid till Date: " + billers.paidAmount)
            }
          }
        }
      }
  }
}
