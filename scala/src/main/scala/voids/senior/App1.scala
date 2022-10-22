package voids.senior

import scala.io.StdIn.readLine

/**
 * Step #1
 * + Add IO monad.
 * + Add Console trait.
 * + Create program.
 */
object App1 {

  // case class has an implicit `apply()` method
  sealed case class IO[A](run: () => A) {
    final def map[B](f: A => B): IO[B] = IO(() => f(run()))
    final def flatMap[B](f: A => IO[B]): IO[B] = IO(() => f(run()).run())
  }

  // encapsulate the console
  trait Console[F[_]] {
    def writeStrLn(line: String): F[Unit]
    def readStrLn: F[String]
  }
  val ConsoleIO: Console[IO] = new Console[IO] {
    override def writeStrLn(line: String): IO[Unit] = IO(() => println(line))
    override def readStrLn: IO[String] = IO(() => readLine())
  }

  def main(args: Array[String]): Unit = {
    val program = for {
      _    <- ConsoleIO.writeStrLn("Kako se zoveš?")
      name <- ConsoleIO.readStrLn
      _    <- ConsoleIO.writeStrLn("Koliko godina programiraš?")
      age  <- ConsoleIO.readStrLn
      _    <- IO(() => {
        // the old code, just copy/paste
        age.toInt match {
          case x if 0 until 10 contains x => println(s"${name} je junior!")
          case x if 10 until 20 contains x => println(s"${name} je senior!")
          case _ => println(s"${name} je baš star/a!")
        }
      })
    } yield ()

    program.run()
  }

}
