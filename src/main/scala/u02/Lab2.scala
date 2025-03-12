package u02

import u02.Lab2.Expr.{add, evaluate, literal, multiply, show}

import scala.Byte.MinValue
import math.Integral.Implicits.infixIntegralOps

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
  println(genericComp(b1, b2)(true)) //true

  //Arbitrary functional composition of three functions
  def composeThree[A, B, C, D](f: C => D, g: B => C, h: A => B): A => D = f compose g compose h

  def t1: (String) => String = _ + "!"

  def t2[A]: (A) => String = _.toString()

  def t3: (Int) => Int = _ * 2

  println(composeThree(t1, t2, t3)(3)) //6!
  println(genericComp(t1, genericComp(t2, t3))(3)) //6!

  //Task 3 - svolto da sola
  //Power senza tail recursion
  def power(base: Double, exponent: Int): Double = exponent match
    case i if i > 0 => base * power(base, exponent - 1)
    case 0 => 1
    case _ => ???

  println(power(2, 3)) //8.0
  println(power(5, 2)) //25.0

  def tailPower(base: Double, exponent: Int): Double =
    @annotation.tailrec
    def _power(base: Double, exponent: Int, acc: Double): Double = exponent match
      case 0 => acc
      case i if i > 0 => _power(base, exponent - 1, base * acc)

    _power(base, exponent, 1)

  println(tailPower(2, 3)) //8.0
  println(tailPower(5, 2)) //25.0

  //Reverse digit
  def reverseNumber(n: Int): Int =
    @annotation.tailrec
    def _reverseNumber(original: Int, reversed: Int): Int =
      val reversedNum: Int = reversed * 10 + (original % 10)
      val leftoverNum: Int = original / 10
      leftoverNum match
        case 0 => reversedNum
        case _ => _reverseNumber(leftoverNum, reversedNum)

    _reverseNumber(n, 0)

  println(reverseNumber(12345)) //54321
  println(reverseNumber(1)) //1
  println(reverseNumber(0)) //0
  println(reverseNumber(-123)) //-321

  //Task 4 - svolto da sola

  enum Expr:
    case literal(num: Int)
    case add(expr: Expr, expr2: Expr)
    case multiply(expr: Expr, expr2: Expr)

  object Expr:
    def evaluate(expr: Expr): Int = expr match
      case Expr.literal(n) => n
      case Expr.add(expr, expr2) => evaluate(expr) + evaluate(expr2)
      case Expr.multiply(expr, expr2) => evaluate(expr) * evaluate(expr2)

    def show(expr: Expr): String = expr match
      case Expr.literal(n) => n.toString
      case Expr.add(expr, expr2) => "( " + show(expr) + " + " + show(expr2) + " )"
      case Expr.multiply(expr, expr2) => "( " + show(expr) + " * " + show(expr2) + " )"

  val e = Expr.add(Expr.literal(2), Expr.multiply(Expr.literal(1), Expr.literal(3)))
  println(evaluate(e))
  println(show(e))

}
