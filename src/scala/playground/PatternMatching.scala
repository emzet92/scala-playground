package scala.playground

object PatternMatching extends App {

  abstract class Pet {
    def makeSound(): String
  }

  case class Cat() extends Pet {
    override def makeSound(): String = "Meow!"
  }

  case class Dog() extends Pet {
    override def makeSound(): String = "Woof!"
  }

  case class Hamster() extends Pet{
    override def makeSound(): String = "Hrrum"
  }

  val dog = new Dog
  val cat = new Cat

  def matchIt(pet: Pet): String = {
    pet match {
      case Dog() => pet.makeSound()
      case Cat() => pet.makeSound()
      case _ => s"Not recognized $pet"
    }
  }

  println(matchIt(new Dog))
  println(matchIt(new Hamster))

}


