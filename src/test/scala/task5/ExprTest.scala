package task5

import org.junit.*
import org.junit.Assert.*
import task5.Optionals.*
import u02.Lab2.Expr
import u02.Lab2.Expr.{evaluate, show}

class ExprTest:

  val num: Int = 3
  val literal: Expr = Expr.literal(num)
  val add: Expr = Expr.add(literal, literal)
  val multiply: Expr = Expr.multiply(literal, literal)

  @Test def literalIsFine(): Unit = assertNotNull(literal)

  @Test def evaluateLiterals(): Unit = assertEquals(num, evaluate(literal))

  @Test def showLiteral(): Unit = assertEquals(num.toString, show(literal))

  @Test def evaluateAdd(): Unit = assertEquals(num + num, evaluate(add))

  @Test def showAdd(): Unit = assertEquals("( " + num + " + " + num + " )", show(add))

  @Test def evaluateMultiply(): Unit = assertEquals(num * num, evaluate(multiply))

  @Test def showMultiply(): Unit = assertEquals("( " + num + " * " + num + " )", show(multiply))

