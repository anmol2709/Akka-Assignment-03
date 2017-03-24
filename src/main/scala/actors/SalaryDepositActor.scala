package actors

import akka.actor.SupervisorStrategy.{Escalate, Stop, Restart, Resume}
import akka.actor._
import models.Response
import scala.concurrent.duration._
import Exception._

class SalaryDepositActor(ref:ActorRef)  extends Actor with ActorLogging {

  override val supervisorStrategy =

    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 second) {
      case nullPointerException:NullPointerException => Stop
    }

  override def receive: Receive = {
case (accountNumber:Int,salary:Long,userName:String) =>
  val response = ref tell ((accountNumber,salary,userName),self)

case response: Response=> {
  val phoneChildActor=context.actorOf(Props[PhoneBillProcessorActor])
  val electricityChildActor=context.actorOf(Props[ElectricityBillProcessorActor])
  val foodChildActor=context.actorOf(Props[FoodBillProcessorActor])
  val internetChildActor=context.actorOf(Props[InternetBillProcessorActor])
  val carChildActor=context.actorOf(Props[CarBillProcessorActor])


phoneChildActor forward(response)
electricityChildActor forward(response)
carChildActor forward(response)
foodChildActor forward(response)
internetChildActor forward(response)

}



}

}
