package voids.book.p02_domain

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName: String = s"$firstName $surname"
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge: Int = year - author.year
  def isWrittenBy(author: Writer): Boolean = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Counter(val count: Int = 0) {
  def inc: Counter = new Counter(count + 1)
  def dec: Counter = new Counter(count - 1)
  def inc(n: Int): Counter = if (n <= 0) this else inc.inc(n - 1)
  def dec(n: Int): Counter = if (n <= 0) this else dec.dec(n - 1)
  def print: Unit = println(count)
}

@main def novelsMain(): Unit = {
  val author = new Writer("Dante", "Alighieri", 1265)
  val novel = new Novel("Divine Comedy", 1595, author)

  val counter = new Counter
  counter.inc.inc.inc.print

}