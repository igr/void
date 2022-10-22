package voids.book.p01_helloworld

@main def controlStructures: Unit = {
  val d = DataTypes()

  /** if  **/

  // `if` is an expression

  val x = if d.c < 3 then 1 else 2

  // An expression returns a result, while a statement does not.
  // Statements are typically used for their side-effects, such as using println to print to the console.

  /** for  **/

  val ints = List(1, 2, 3, 4, 5)

  // The code `i <- ints` is referred to as a `generator`,
  // and the code that follows the do keyword is `the body of the loop`.

  for i <- ints do println(i)

  for
    i <- ints
    if i > 2    // You can also use one or more if expressions inside a for loop. These are referred to as guards.
  do
    println(i)

  // You can use multiple generators and guards.

  for
    i <- 1 to 3
    j <- 'a' to 'c'
    if i == 2
    if j == 'b'
  do
    println(s"i = $i, j = $j")   // prints: "i = 2, j = b"

  /** yield  **/

  // When you use the `yield` keyword instead of `do`,
  // you create for expressions which are used to calculate and yield results.

  val doubles = for i <- ints yield i * 2

  // Capitalize the first character in each string in the list:
  val names = List("chris", "ed", "maurice")
  val capNames = for name <- names yield name.capitalize

  /** match  **/
  def getClassAsString(x: Matchable): String = x match
    case s: String => s"'$s' is a String"
    case i: Int => "Int"
    case d: Double => "Double"
    case l: List[_] => "List"
    case _ => "Unknown"

  println(getClassAsString(1))
  println(getClassAsString("hello"))
  println(getClassAsString(List(1, 2, 3)))

}
