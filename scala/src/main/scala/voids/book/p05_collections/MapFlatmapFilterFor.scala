package voids.book.p05_collections

object MapFlatmapFilterFor extends App {

  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')

  val combinations = numbers.flatMap(n => chars.map(c => "" + n + c))
  println(combinations)

  // as it is hard to read, we can use for comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0    // guard i.e. filter
    c <- chars
  } yield "" + n + c
  println(forCombinations)

}
