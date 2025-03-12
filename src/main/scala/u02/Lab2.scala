package u02

import scala.Byte.MinValue

object Lab2 extends App {


  //Task 1 - svolto da sola
  def div(x: Double, y: Double): Double = x / y

  def curriedDiv(x: Double)(y: Double): Double = x / y

  //Test div
  println("Test div")
  println(div(6, 2)) //3
  println(div(4, 0)) //Infinity
  println(div(-4, 0)) //-Infinity
  println(div(0, 0)) //Nan

  //Test curried div
  println("Test curried div")
  println(curriedDiv(6)(2)) //3
  println(curriedDiv(4)(0)) //Infinity
  println(curriedDiv(-4)(0)) //-Infinity
  println(curriedDiv(0)(0)) //Nan
  println(curriedDiv) //lambda
  val a = curriedDiv(3)
  println(a) //lambda 3x
  println(a(4)) //0.75

  //Task 2 - svolto da sola

  val positiveLambda: (Int) => String = _ match
    case p if p >= 0 => "positive"
    case _ => "negative"

  def positiveFunction(i: Int): String = i match
    case n if n < 0 => "negative"
    case _ => "positive"

  println(positiveLambda(-1)) //negative
  println(positiveLambda(3)) //positive
  println(positiveLambda(0)) //positive
  println(positiveFunction(-1)) //negative
  println(positiveFunction(3)) //positive
  println(positiveFunction(0)) //positive

  def neg(f: (String) => Boolean): (String => Boolean) = !f(_)

  val empty: String => Boolean = _ == ""
  println(neg(empty)("test")) //true
  println(neg(empty)("")) //false

  def genericNeg[X](f: (X) => Boolean): (X => Boolean) = !f(_)

  val zero: Int => Boolean = _ == 0

  println(genericNeg(zero)(1)) //true
  println(genericNeg(empty)("")) //false


  //Curring
  val p1: Int => Int => Int => Boolean = (x: Int) => (y: Int) => (z: Int) => (y == z) && (x <= y) //Curried lambda
  val p2: (Int, Int, Int) => Boolean = (x: Int, y: Int, z: Int) => (y == z) && (x <= y) //Un-curried lambda

  def p3(x: Int)(y: Int)(z: Int): Boolean = (y == z) && (x <= y) //Curried function

  def p4(x: Int, y: Int, z: Int): Boolean = (y == z) && (x <= y) //Un-curried function

  //true
  println(p1(1)(1)(1))
  println(p2(1, 1, 1))
  println(p3(1)(1)(1))
  println(p4(1, 1, 1))

  //false
  println(p1(1)(1)(2))
  println(p2(1, 1, 2))
  println(p3(1)(1)(2))
  println(p4(1, 1, 2))

  //Composition, f compose g
  //TODO: Check if this is ok
  def comp(f: Int => Int, g: Int => Int): Int => Int = x => f.apply(g(x))

  println(comp(_ - 1, _ * 2)(5)) // 9

  def genericComp[X, Y, Z](f: Y => Z, g: X => Y): X => Z = f compose g

  val f1: (Double) => Double = _ - 1
  val f2: (Double) => Double = _ * 2

  val b1: (Boolean) => Boolean = !_
  val b2: (Boolean) => Boolean = !_

  println(genericComp(f1, f2)(5)) // 9.0
  println(genericComp(b1,b2)(true)) //true

}
