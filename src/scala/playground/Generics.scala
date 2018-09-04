package scala.playground

object Generics extends App{
  class MyList[+A]{
    def add[B >: A](element: B): MyList[B] = ???
  }

  object MyList{
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem

  class Animal
  class Cat  extends Animal
  class Dog extends Animal

  // yes List[Cat[ extends List[Animal]
  class CovariantList[+A] {

  }

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  val animalList2: CovariantList[Animal] = new CovariantList[Dog]

  class InvariantList[A]
  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  // lower bounded type
  class Cage[A <: Animal](animal: A) // only subtypes of animal
  val cage = new Cage(new Dog())



}
