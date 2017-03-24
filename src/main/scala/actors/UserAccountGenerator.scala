package actors

import akka.actor.SupervisorStrategy.{Escalate, Stop, Restart, Resume}
import akka.actor.{OneForOneStrategy, Actor, ActorRef}
import akka.dispatch.{BoundedMessageQueueSemantics, RequiresMessageQueue}
import models.{BillerDetails, UserAccountDetails}
import scala.concurrent.duration._

class UserAccountGenerator(ref:ActorRef) extends Actor  with RequiresMessageQueue[BoundedMessageQueueSemantics]{
  import Exception._
  override val supervisorStrategy =

    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 second) {

      case arithmeticPointerException:ArithmeticException => Resume
      case nullPointerException:NullPointerException => Restart
      case illegalArgumentException:IllegalArgumentException => Stop
      case _: Exception => Resume
    }

  override def receive: Receive = {

    case userDetails: UserAccountDetails => ref ! userDetails

    case billerDetails: BillerDetails => ref ! billerDetails


  }
}