package u02

import scala.Byte.MinValue

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

  def comp(f: Int => Int, g: Int => Int): Int => Int = x=> f.apply(g(x))
    //f compose g

  println(comp(_ - 1, _ * 2)(5)) // 9

  def genericComp[X,Y,Z](f: Y => Z, g: X => Y): X => Z = f compose g

  //println(genericComp(_ - 1, _ * 2)(5)) // 9 //TODO: fix this es2.5

}
