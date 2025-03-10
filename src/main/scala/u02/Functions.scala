package u02

object Functions extends App{

  val x= 5

  val positiveLambda:(Int)=>String = _ match
    case p if p>=0 => "positive"
    case _ => "negative"

  println(positiveLambda(-1))
  println(positiveLambda(3))

  def positiveFunction(i :Int):String= i match
    case n if n<0 =>"negative"
    case _=>"positive"

  println(positiveFunction(-1))
  println(positiveFunction(3))

  val empty: String => Boolean = _ == ""

  def neg (f:(String)=>Boolean): (String=>Boolean) = !f(_)

  println(neg(empty)("test"))

  def genericNeg [X] (f:(X)=>Boolean): (X=>Boolean) = !f(_)

  val zero: Int => Boolean = _ == 0

  println(genericNeg(zero)(1))

}
