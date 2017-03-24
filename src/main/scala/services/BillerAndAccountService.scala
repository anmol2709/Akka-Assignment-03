package services

import akka.actor.ActorRef
import models._


class BillerAndAccountService {
  def billingInfoGenerator(maximum: Int, ref: ActorRef) = {

    for (count <- 1 to maximum) {
      val accountNumber = count
      val nameOfAccountHolder = s"User$count"
      val address = "Faridabad"
      val userName = s"User$count"
      val initialAmount = 0

      ref ! UserAccountDetails(accountNumber, nameOfAccountHolder, address, userName, initialAmount)
      for (category <- 1 to 5) {

        if (category == 1) {
          val billerCatgory =Phone
          val billerName = "Vodafone"
          val transactionDate = "01/03/2017"
          val amount = 420
          val paidAmount = 0


          ref ! BillerDetails(billerCatgory, billerName, accountNumber, transactionDate, amount,paidAmount)

        }

        if (category == 2) {
          val billerCatgory =Electricity
          val billerName ="Reliance"
          val transactionDate ="01/03/2017"
          val amount = 600
          val paidAmount = 0

          ref ! BillerDetails(billerCatgory, billerName, accountNumber, transactionDate, amount,paidAmount)

        }

        if (category == 3) {
          val billerCatgory =Internet
          val billerName ="Airtel"
          val transactionDate ="01/03/2017"
          val amount = 1200
          val paidAmount = 0

          ref ! BillerDetails(billerCatgory, billerName, accountNumber, transactionDate, amount,paidAmount)

        }

        if (category == 4) {
          val billerCatgory =Food
          val billerName ="FoodPanda"
          val transactionDate ="01/03/2017"
          val amount = 400
          val paidAmount = 0

          ref ! BillerDetails(billerCatgory, billerName, accountNumber, transactionDate, amount,paidAmount)

        }

        if (category == 5) {
          val billerCatgory =Car
          val billerName ="Honda"
          val transactionDate ="01/03/2017"
          val amount = 12000
          val paidAmount = 0

          ref ! BillerDetails(billerCatgory, billerName, accountNumber, transactionDate, amount,paidAmount)

        }
      }


    }

  }
}
