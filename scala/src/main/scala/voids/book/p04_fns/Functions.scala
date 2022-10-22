package voids.book.p04_fns

class Functions extends App {

  var doubler = new Function1[Int, Int] {
    def apply(x: Int): Int = x * 2
  }

  println(doubler(2))

  var doubler2: Int => Int = (x: Int) => x * 2    // LAMBDA

  println(doubler2(2))

  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b
}
