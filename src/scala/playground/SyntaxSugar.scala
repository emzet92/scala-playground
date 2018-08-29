package scala.playground

object SyntaxSugar extends App {
  class Person(val name: String, val favouriteMovie: String){
    def likes(person: Person):Person = this
    def andTheyLoveToWatch(movie: String): Boolean = true
  }


  val bob = new Person("Bob", "Avengers")
  val alice = new Person("Alice", "Avengers")

  bob likes alice andTheyLoveToWatch "Avengers"

}
