package actors

import akka.actor.{ActorLogging, Actor}
import models.{Food, Response}


class FoodBillProcessorActor extends Actor with ActorLogging {
  override def receive: Receive = {

    case response:Response => sender() ! (response,Food)

  }
}