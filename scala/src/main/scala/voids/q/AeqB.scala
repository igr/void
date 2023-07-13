package voids.q
import scala.language.implicitConversions

object AeqB {

  class A
  class B

  def main(args: Array[String]): Unit = {
    implicit def bToA(b: B): A = A()

    val a:A = B()
  }

}
