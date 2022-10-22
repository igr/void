package voids.book.p02_domain

// Scala traits can be used as simple interfaces, but they can also contain abstract and concrete methods and fields,
// and they can have parameters, just like classes. They provide a great way for you to organize behaviors into small,
// modular units. Later, when you want to create concrete implementations of attributes and behaviors, classes and
// objects can extend traits, mixing in as many traits as needed to achieve the desired behavior.

trait Speaker:
  def speak(): String  // has no body, so it’s abstract

trait TailWagger:
  def startTail(): Unit = println("tail is wagging")
  def stopTail(): Unit = println("tail is stopped")

trait Runner:
  def startRunning(): Unit = println("I’m running")
  def stopRunning(): Unit = println("Stopped running")

class Dog(name: String) extends Speaker, TailWagger, Runner:
  def speak(): String = "Woof!"

class Cat(name: String) extends Speaker, TailWagger, Runner:
  def speak(): String = "Meow"
  override def startRunning(): Unit = println("Yeah ... I don’t run")
  override def stopRunning(): Unit = println("No need to stop")

@main def traits() = {
  val d = Dog("Rover")
  println(d.speak()) // prints "Woof!"

  val c = Cat("Morris")
  println(c.speak()) // "Meow"
  c.startRunning() // "Yeah ... I don’t run"
  c.stopRunning() // "No need to stop"
}