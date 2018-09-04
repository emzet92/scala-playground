package scala.playground

object FunctionalProgrammingBasics extends App {
  trait MyFunction[A, B]{
    def apply(element: A): B
  }

  def concatenator: (String, String) => String = (v1: String, v2: String) => s"$v1 $v2"
  println(concatenator("ala", "ma kota"))

  val adder: Function1[Int, Function1[Int, Int]] = (v1: Int) => (v2: Int) => v1 + v2
  val adder3 = adder(3)
  println(adder3(4))
  println(adder(3)(4)) // curried functions
}
