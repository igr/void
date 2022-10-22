package voids.book.p04_fns

object HOFsCurries extends App {
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???
  val function: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???
  val curriedFunction: (Int, String, Int => Boolean) => Int => Int = ???
  val firstArg: Int => (String, Int => Boolean) => Int = ???
  val secondArg: String => Int => (Int => Boolean) = ???
  val thirdArg: Int => Boolean => Int = ???


  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  // curriend functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3)
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
}
