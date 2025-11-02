package voids.variants

object DogCat {

  def main(args: Array[String]): Unit = {
    abstract class Animal:
      def name: String

    case class Cat(name: String) extends Animal
    case class Dog(name: String) extends Animal

    class Box[A](var content: A)

    val myCatBox: Box[Cat] = Box[Cat](Cat("Felix"))
    val myAnimalBox: Box[Animal] = null // = myCatBox - this does not compile

    // From this, we have to conclude that Box[Cat] and Box[Animal]
    // can’t have a subtyping relationship, even though Cat and Animal do.

    // COVARIANCE EXAMPLE

    class ImmutableBox[+A](val content: A)
    val catBox: ImmutableBox[Cat] = ImmutableBox[Cat](Cat("Felix"))
    val animalBox: ImmutableBox[Animal] = catBox // now this compiles

    // given some class `Cov[+T]`, then if `A` is a subtype of `B`,
    // `Cov[A]` is a subtype of `Cov[B]`.
    // This allows us to make very useful and intuitive subtyping
    // relationships using generics.

    // We’ve seen we can accomplish covariance by making sure that we
    // can’t put something in the covariant type, but only get something out

  }

}
