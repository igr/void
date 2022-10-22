package voids.book.p02_domain

// define a case class

// + Case class constructor parameters are public val fields by default, so the fields are immutable,
// and accessor methods are generated for each parameter.
// + An `unapply` method is generated, which lets you use case classes in more ways in match expressions.
// + A copy method is generated in the class. This provides a way to create updated copies of the object
// without changing the original object.
// + `equals` and `hashCode` methods are generated to implement structural equality.
// A default `toString` method is generated, which is helpful for debugging.

case class Person(
  name: String,
  vocation: String
)

@main def cases() = {
  // create an instance of the case class
  val p = Person("Reginald Kenneth Dwight", "Singer")

  // a good default toString method
  println(p)                // : Person = Person(Reginald Kenneth Dwight,Singer)

  // can access its fields, which are immutable
  println(p.name)           // "Reginald Kenneth Dwight"
  //p.name = "Joe"   // error: can’t reassign a val field

  // when you need to make a change, use the `copy` method
  // to “update as you copy”
  val p2 = p.copy(name = "Elton John")
  println(p2)
}