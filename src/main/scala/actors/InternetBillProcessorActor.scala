package actors

import akka.actor.{ActorLogging, Actor}
import models.{Internet, Response}

/**
  * Created by knoldus on 23/3/17.
  */
class InternetBillProcessorActor extends Actor with ActorLogging {
  override def receive: Receive = {

    case response:Response => sender() ! (response,Internet)

  }

}
