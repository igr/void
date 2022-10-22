package voids.senior

import scala.io.StdIn.readLine
import scala.util.Try

object App4 {

  sealed case class IO[A](run: () => A) {
    final def map[B](f: A => B): IO[B] = IO(() => f(run()))
    final def flatMap[B](f: A => IO[B]): IO[B] = IO(() => f(run()).run())
  }

  trait Console[F[_]] {
    def writeStrLn(line: String): F[Unit]
    def readStrLn: F[String]
  }
  object Console {
    // add `apply`
    def apply[F[_]](implicit F: Console[F]): Console[F] = F

    implicit val ConsoleIO: Console[IO] = new Console[IO] {
      def writeStrLn(line: String): IO[Unit] = IO(() => println(line))
      def readStrLn: IO[String] = IO(() => readLine())
    }
  }

  // ---

  def writeStrLn[F[_]: Console](text: Text): F[Unit] = Console[F].writeStrLn(text in serbian)
  def readStrLn[F[_]: Console](): F[String] = Console[F].readStrLn
  def parseInt(input: String): Option[Int] = Try(input.toInt).toOption

  // ---

  sealed abstract case class Age() {
    def message(name: String): Text
  }
  object Age {
    def of(age: Int): Option[Age] = age match {
      case x if 0 until 10 contains x => Some(Junior)
      case x if 10 until 20 contains x => Some(Senior)
      case x if x > 20 => Some(Star)
      case _ => None
    }
  }
  object Junior extends Age {
    override def message(name: String): Text = Text.Junior(name)
  }
  object Senior extends Age {
    override def message(name: String): Text = Text.Senior(name)
  }
  object Star extends Age {
    override def message(name: String): Text = Text.Star(name)
  }

  def answer(name: String, age: Option[Int]): Text =
    age
      .fold[Text](Text.InvalidInput)(
        Age.of(_).fold[Text](Text.InvalidAge)(
          age => age.message(name)))


  sealed trait Text {
    def flatMap(f: Text => IO[Unit]): IO[Unit] = IO(() => f(this).run())

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

  def main[F[_]]: IO[Unit] = for {
    _         <- writeStrLn(Text.WhatsYourName)
    name      <- readStrLn()
    _         <- writeStrLn(Text.HowManyYears)
    age       <- readStrLn().map(_.trim).map(parseInt)
    txt       <- answer(name, age)
    _         <- writeStrLn(txt)
  } yield ()

  def main(args: Array[String]): Unit = {
    main[IO].run()
  }

}
