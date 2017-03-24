package com.knoldus

import actors.{ReportGeneratorActor, UserAccountGenerator, SalaryDepositActor, DatabaseActor}
import akka.actor.{Props, ActorSystem}
import models.FetchReport
import services.{SalaryDepositService, BillerAndAccountService}

object AutomatedAccountingSystem extends App{
  val billerObject = new BillerAndAccountService
  val salaryObject = new SalaryDepositService
  val system = ActorSystem("AutomatedAccountingSystem")
  val databaseActor = system.actorOf(Props[DatabaseActor],"DatabaseActor")
  val userAccountGenerator = system.actorOf(Props(classOf[UserAccountGenerator], databaseActor), "UserAccountGenerator")
  val salaryDepositActor = system.actorOf(Props(classOf[SalaryDepositActor], databaseActor), "SalaryDepositActor")
  val reportGeneratorActor = system.actorOf(Props(classOf[ReportGeneratorActor], databaseActor), "ReportGeneratorActor")

  billerObject.billingInfoGenerator(100, userAccountGenerator)

  salaryObject.salaryUpdate(20000,100,salaryDepositActor)

  reportGeneratorActor ! FetchReport

}
