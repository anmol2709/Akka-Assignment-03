package services

import akka.actor.ActorRef

class SalaryDepositService {
def salaryUpdate(salary:Long,maximum:Int,ref:ActorRef)= {
  for (accountNumber <- 1 to maximum){


    ref ! (accountNumber,salary,s"User$accountNumber")
  }
}}
