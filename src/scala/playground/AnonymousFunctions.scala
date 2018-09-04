package scala.playground

object AnonymousFunctions extends App{
  // anonymous function / lambda
  val doubler = (a: Int) => a * 2

  // multiple parameters lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  val doSth: () => Int = () => 3

  println(doSth)
  println(doSth())

  // syntax sugar
  val incrementer: Int => Int = _ + 1
  val niceAdder: (Int, Int) => Int = _ + _
}
