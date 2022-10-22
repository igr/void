package voids.senior

import scala.io.StdIn.readLine
import scala.util.Try

object App3 {

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

  def writeStrLn(text: Text): IO[Unit] = ConsoleIO.writeStrLn(text in serbian)
  def readStrLn(): IO[String] = ConsoleIO.readStrLn
  def parseInt(input: String): Option[Int] = Try(input.toInt).toOption

  // ---

  sealed abstract class Age(val ageFrom: Int, val ageTo: Option[Int]) {
    def message(name: String): Text
  }
  object Age {
    def of(age:Int): Option[Age] = age match {
      case x if 0 until 10 contains x => Some(Junior)
      case x if 10 until 20 contains x => Some(Senior)
      case x if x > 20 => Some(Star)
      case _ => None
    }
  }
  object Junior extends Age(0, Some(10)) {
    override def message(name: String): Text = Text.Junior(name)
  }
  object Senior extends Age(10, Some(20)) {
    override def message(name: String): Text = Text.Senior(name)
  }
  object Star extends Age(20, None) {
    override def message(name: String): Text = Text.Star(name)
  }

  def answer(name: String, age: Option[Int]): IO[Unit] =
    age
      .fold(writeStrLn(Text.InvalidInput))(
        a => Age.of(a).fold(writeStrLn(Text.InvalidAge))(
          a => writeStrLn(a.message(name))))


  sealed trait Text {
    def en: String
    def rs: String
    def in(f: (Text) => String): String = f(this)
  }
  object Text {
    case object WhatsYourName extends Text {
      override def en: String = "Whats your name?"
      override def rs: String = "Kako se zoveš?"
    }
    case object HowManyYears extends Text {
      override def en: String = "How many years?"
      override def rs: String = "Kako godina programiraš?"
    }
    case object InvalidInput extends Text {
      override def en: String = "Invalid input"
      override def rs: String = "Nepravilan unos"
    }
    case object InvalidAge extends Text {
      override def en: String = "Invalid age"
      override def rs: String = "Neispravne godine"
    }
    case class Junior(name: String) extends Text {
      override def en: String = s"${name} is junior"
      override def rs: String = s"${name} je junior"
    }
    case class Senior(name: String) extends Text {
      override def en: String = s"${name} is senior"
      override def rs: String = s"${name} je senior"
    }
    case class Star(name: String) extends Text {
      override def en: String = s"${name} is a star"
      override def rs: String = s"${name} je star/a"
    }
  }
  def serbian(text: Text): String = text.rs

  val mainIO: IO[Unit] = for {
    _         <- writeStrLn(Text.WhatsYourName)
    name      <- readStrLn()
    _         <- writeStrLn(Text.HowManyYears)
    age       <- readStrLn().map(_.trim).map(parseInt)
    _         <- answer(name, age)
  } yield ()

  def main(args: Array[String]): Unit = {
    mainIO.run()
  }
}
