package voids.book.p02_domain

import scala.math.*

// An object is a class that has exactly one instance. It’s initialized lazily
// when its members are referenced, similar to a lazy val.
object StringUtils:
  def truncate(s: String, length: Int): String = s.take(length)
  def containsWhitespace(s: String): Boolean = s.matches(".*\\s.*")
  def isNullOrEmpty(s: String): Boolean = s == null || s.trim.isEmpty


/* COMPANION OBJECTS */

// Companion objects can be used for several purposes:

// + As shown, they can be used to group “static” methods under a namespace
//   These methods can be public or private
//   If calculateArea was public, it would be accessed as Circle.calculateArea
// + They can contain apply methods, which—thanks to some syntactic sugar—work as
//   factory methods to construct new instances
// + They can contain unapply methods, which are used to deconstruct objects, such as with pattern matching

case class Circle(radius: Double):
  def area: Double = Circle.calculateArea(radius)

// An object that has the same name as a class, and is declared in the same file as the class, is called a
// “companion object.”
object Circle:
  private def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)

val circle1 = Circle(5.0)
// circle1.area



// Example of apply

class Person2:
  var name = ""
  var age = 0
  override def toString = s"$name is $age years old"

object Person2:

  // a one-arg factory method
  def apply(name: String): Person2 =
    var p = new Person2
    p.name = name
    p

  // a two-arg factory method
  def apply(name: String, age: Int): Person2 =
    var p = new Person2
    p.name = name
    p.age = age
    p

end Person2

val joe = Person2("Joe")
val fred = Person2("Fred", 29)