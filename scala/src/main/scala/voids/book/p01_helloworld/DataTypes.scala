package voids.book.p01_helloworld

class DataTypes {
  // mutable variable
  var c = 1
  c = 2

  // immutable variable with explicit types
  val b: Byte = 1
  val i: Int = 1
  val l: Long = 1
  val s: Short = 1
  val d: Double = 2.0
  val f: Float = 3.0

  // multiline string
  val quote = """The essence of Scala:
                |Fusion of functional and object-oriented
                |programming in a typed setting.""".stripMargin

}