package actors

import akka.actor.{ActorLogging, Actor}
import models.{Electricity, Response}

class ElectricityBillProcessorActor extends Actor with ActorLogging {
  override def receive: Receive = {

    case response:Response => sender() ! (response,Electricity)

  }
}