package voids.variants

class List[+A]    // Covariant in A
class Printer[-A] // Contravariant in A

class Animal
class Bird extends Animal
class Ostrich extends Bird

// The subtype relationships are as follows:
// Nothing <: Ostrich <: Bird <: Animal <: Any
// more specific to more general


def transformPrinter(f: Printer[Bird] => List[Bird]): Unit = ()
// A function type A => B is actually shorthand for Function1[-A, +B]
// Functions are contravariant in parameters and covariant in results.

val a: Unit = transformPrinter(null : Any => Nothing)

val b: Unit = transformPrinter(null : Printer[Nothing] => List[Nothing])

val c: Unit = transformPrinter(null : Printer[Ostrich] => List[Ostrich])

// Ostrich <: Bird ✅
// Printer[Bird] <: Printer[Ostrich] ✅ Contravariant
// List[Ostrich] <: List[Bird] ✅ Covariant
// Therefore:
// Printer[Ostrich] => List[Ostrich] <: Printer[Bird] => List[Bird]


//val d: Unit = transformPrinter(null : Printer[Animal] => List[Ostrich])
//
//val e: Unit = transformPrinter(null : Printer[Ostrich] => List[Animal])
//
//val f: Unit = transformPrinter(null : Printer[Animal] => List[Animal])
