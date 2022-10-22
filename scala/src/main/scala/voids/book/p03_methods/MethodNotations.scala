package voids.book.p03_methods

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)

    def unary_! : String = s"$name, what the heck?!"

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"

    def learns(thing: String) = s"$name is learning $thing"

    def learnsScala = this learns "Scala"
  }

  object Person {
    // this is a factory method, but automatically added for the companion objects
    //def apply(name: String, favoriteMovie: String) = new Person(name, favoriteMovie)
  }


  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")

  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  val x = -1
  val y = 1.unary_-

  println(!mary)
  println(mary.unary_!)

  println(mary.isAlive)

  println(mary.apply())
  println(mary()) // equivalent

  println((mary + "the Rockstar").apply())
  println((+mary).age)
  println(mary learnsScala)
  println(mary(10))

  // ctort vs companion object with apply

  val person1 = new Person("Bob", "Fight Club")
  val person2 = Person("Mary", "Inception")


}
