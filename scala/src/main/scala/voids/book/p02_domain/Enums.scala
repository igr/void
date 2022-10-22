package voids.book.p02_domain

// MUST import enums!
import CrustSize.*

enum CrustSize:
  case Small, Medium, Large

enum CrustType:
  case Thin, Thick, Regular

enum Topping:
  case Cheese, Pepperoni, BlackOlives, GreenOlives, Onions

@main def enums() = {
  val currentCrustSize = Small

  // enums in a `match` expression
  currentCrustSize match
    case Small => println("Small crust size")
    case Medium => println("Medium crust size")
    case Large => println("Large crust size")

  // enums in an `if` statement
  if currentCrustSize == Small then println("Small crust size")
}


// parametrized enums
enum Color(val rgb: Int):
  case Red   extends Color(0xFF0000)
  case Green extends Color(0x00FF00)
  case Blue  extends Color(0x0000FF)