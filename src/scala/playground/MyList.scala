package scala.playground

trait MyPredicate[-T] {
  def test(arg: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}


abstract class MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements(): String

  override def toString = s"[ $printElements ]"

  def map[B](transformer: MyTransformer[A, B]): MyList[B]

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  def filter(myPredicate: MyPredicate[A]): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)

  override def printElements(): String = ""

  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  override def filter(myPredicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}


case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = Cons(element, this)

  override def printElements(): String = {
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"
  }

  override def map[B](transformer: MyTransformer[A, B]): MyList[B] = Cons(transformer.transform(h), t.map(transformer))

  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = transformer.transform(h) ++ t.flatMap(transformer)

  override def filter(myPredicate: MyPredicate[A]): MyList[A] = {
    if (myPredicate.test(h)) Cons(h, t.filter(myPredicate))
    else t.filter(myPredicate)
  }

  override def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)
}

object ListApp extends App {
  val listOfIntegers = Cons(1, Cons(2, Cons(3, Empty)))
  val anotherList = Cons(3, Cons(2, Cons(1, Empty)))
  val stringList = Cons("Scala", Cons("is", Cons("Awesome", Empty)))

  println(listOfIntegers)
  println(listOfIntegers.tail.head)
  println(listOfIntegers.add(4).head)
  println(stringList)
  println(listOfIntegers.map((element: Int) => element * 2))
  println(listOfIntegers ++ anotherList)
  println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(element: Int): MyList[Int] = Cons(element, Cons(element + 2, Empty))
  }))

}