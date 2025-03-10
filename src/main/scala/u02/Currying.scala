package u02

object Currying extends App :

  // standard function with no currying
  def mult(x: Double, y: Double): Double = x * y

  def div(x: Double, y: Double): Double = x / y

  // function with currying
  // curriedMult has actually type: Double => (Double => Double)
  def curriedMult(x: Double)(y: Double): Double = x * y


  def curriedDiv(x: Double)(y: Double): Double = x / y

  // slightly different syntax at the call side..
  println(mult(10, 2)) // 20
  println(curriedMult(10)(2)) // 20

  // curriedMult can be partially applied!
  val twice: Double => Double = curriedMult(2)

  println(twice(10)) // 20

  // => is right-associative, hence it is equal to:
  //val curr...: Double => (Double => Double) = x => (y => x*y)
  val curriedMultAsFunction: Double => Double => Double = x => y => x * y

  println(curriedMultAsFunction(10)(2)) // 20
  println(curriedMultAsFunction) // u02.Currying$$$Lambda$7/...
  println(curriedMultAsFunction(10)) // u02.Currying$$$Lambda$12/...

  //Test div
  println("div")
  println(div(6,2)) //3
  println(div(4,0)) //Infinity
  println(div(-4,0)) //-Infinity
  println(div(0,0)) //Nan

  //Test curried div
  //Test div
  println("Curried div")
  println(curriedDiv(6) (2)) //3
  println(curriedDiv(4) (0)) //Infinity
  println(curriedDiv(-4) (0)) //-Infinity
  println(curriedDiv(0) (0)) //Nan
  println(curriedDiv)
  println(curriedDiv(3))
  val a=curriedDiv(3)
  println(a(4)) //0.75