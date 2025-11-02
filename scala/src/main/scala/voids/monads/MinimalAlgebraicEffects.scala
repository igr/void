package voids.monads

import cats.*
import cats.effect.*
import cats.effect.std.Console
import cats.implicits.*

// Effect interface (algebra)
trait Counter[F[_]] {
  def increment: F[Unit]
  def get: F[Int]
}

// Business logic - polymorphic in F
object CounterProgram {
  def greet[F[_] : Monad : Console](name: String)(implicit C: Counter[F]): F[String] =
    for {
      _ <- C.increment
      count <- C.get
      msg = s"Hello, $name!"
      _ <- Console[F].println(s"$msg (operations: $count)")
    } yield msg
}

// Concrete interpreter using Ref
object CounterInterpreter {
  def refCounter[F[_] : Sync](ref: Ref[F, Int]): Counter[F] = new Counter[F] {
    def increment: F[Unit] = ref.update(_ + 1)

    def get: F[Int] = ref.get
  }
}

object MinimalAlgebraicEffects extends IOApp.Simple {
  import CounterInterpreter.*
  import CounterProgram.*

  def run: IO[Unit] =
    Ref.of[IO, Int](0).flatMap { ref =>
      implicit val counter: Counter[IO] = refCounter[IO](ref)
      greet[IO]("World").void
    }
}