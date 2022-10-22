package voids.book.p03_methods

// Extension methods let you add new methods to closed classes.

extension (s: String)
  def hello: String = s"Hello, ${s.capitalize}!"
  def aloha: String = s"Aloha, ${s.capitalize}!"


@main def extensions() = {
  println("world".hello)
  println("friend".aloha)
}