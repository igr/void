package voids.drink

object Main {

  case class Person(name: String, age: Int)

  def drink(person: Person): String =
    person match {
      case _ if person.age < 18 => "milk"
      case _ if person.age < 21 => "beer"
      case _ => "whisky"
    }

  def main(args: Array[String]): Unit = {
    val people = Seq(
      Person("Lina", 10),
      Person("Oleg", 16),
      Person("Ema", 18),
      Person("John", 20),
      Person("Jane", 21)
    )

    people.foreach(p => println(s"${p.name} drinks ${drink(p)}"))
    println("-------------------")

    val (adults, minors) = people.partition(_.age >= 18)

    def drinkAlcohol(adults: Seq[Person]): Unit =
      for adult <- adults do
        println(s"${adult.name} drinks ${drink(adult)}")

    drinkAlcohol(minors)
    println("-------------------")

    def drinkAlcohol2(adults: Seq[Adult]): Unit =
      for adult <- adults do
        println(s"${adult.name} drinks ${drink(adult.person)}")

    val adults2 = people.filter(_.age >= 18).map(Adult.apply)

    drinkAlcohol2(adults2)
  }

  final case class Adult(person: Person):
    export person.*

}
