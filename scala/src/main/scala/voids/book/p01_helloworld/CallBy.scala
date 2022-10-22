package voids.book.p01_helloworld

def callByValue(x: Long): Unit = {
  println("by valye " + x)
  println("by valye " + x)
}

def callByName(x: => Long): Unit = {
  println("by name " + x)
  println("by name " + x)
}

@main def main(): Unit = {
  callByValue(System.nanoTime())
  callByName(System.nanoTime())
}