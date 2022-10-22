package voids.senior

import scala.io.StdIn.readLine
import scala.util.Try

object App2 {
  sealed case class IO[A](run: () => A) {
    final def map[B](f: A => B): IO[B] = IO(() => f(run()))
    final def flatMap[B](f: A => IO[B]): IO[B] = IO(() => f(run()).run())
  }

  trait Console[F[_]] {
    def writeStrLn(line: String): F[Unit]
    def readStrLn: F[String]
  }
  val ConsoleIO: Console[IO] = new Console[IO] {
    override def writeStrLn(line: String): IO[Unit] = IO(() => println(line))
    override def readStrLn: IO[String] = IO(() => readLine())
  }

  // ---

  // Simple utility methods, to reduce code
  def writeStrLn(string: String): IO[Unit] = ConsoleIO.writeStrLn(string)
  def readStrLn(): IO[String] = ConsoleIO.readStrLn
  def parseInt(input: String): Option[Int] = Try(input.toInt).toOption

  // encapsulate answering to new method
  def answer(name: String, age: Int): IO[Unit] = {
    age match {
      case x if 0 until 10 contains x => writeStrLn(s"${name} je junior!")
      case x if 10 until 20 contains x => writeStrLn(s"${name} je senior!")
      case _ => writeStrLn(s"${name} je baš star/a!")
    }
  }

  def main(args: Array[String]): Unit = {
    val program = for {
      _    <- writeStrLn("Kako se zoveš?")
      name <- readStrLn()
      _    <- writeStrLn("Koliko godina programiraš?")
      age  <- readStrLn()
      _    <- parseInt(age).fold(writeStrLn("Error"))(ageInt => answer(name, ageInt))
    } yield ()

    program.run()
  }
}
