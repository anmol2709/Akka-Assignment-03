package actors

import akka.actor.{Actor, ActorLogging}
import models.{Car, Response}

/**
  * Created by knoldus on 23/3/17.
  */
class CarBillProcessorActor extends Actor with ActorLogging {
  override def receive: Receive = {

    case response: Response => sender() !(response, Car)

  }
}
