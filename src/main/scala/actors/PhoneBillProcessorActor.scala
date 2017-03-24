package actors



import akka.actor.{ActorLogging, Actor}
import models.{Phone, Response}


class PhoneBillProcessorActor extends Actor with ActorLogging {
  override def receive: Receive = {

    case response:Response => sender() ! (response,Phone)

  }

}
