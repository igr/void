package voids.senior

import scala.io.StdIn.readLine

object App0 {
  def main(args: Array[String]): Unit = {
    println("Kako se zoveš?")
    val name = readLine()
    println("Koliko godina programiraš?")
    readLine().toInt match {
      case x if 0 until 10 contains x => println(s"${name} je junior!")
      case x if 10 until 20 contains x => println(s"${name} je senior!")
      case _ => println(s"${name} je baš star/a!")
    }
  }
}
