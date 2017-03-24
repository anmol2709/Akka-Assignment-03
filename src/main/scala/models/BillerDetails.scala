package models


  case class BillerDetails(billerCategory:Category,
                           billerName:String,
                           accountNumber:Long,
                           transactionDate:String,
                           amount:Int,
                           paidAmount:Int,
                           totalIterations:Int=12,
                           executedIterations:Int=0)


sealed abstract class Category

case object Phone extends Category
case object Electricity extends Category
case object Internet extends Category
case object Food extends Category
case object Car extends Category
