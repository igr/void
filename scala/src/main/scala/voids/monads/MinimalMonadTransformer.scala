package voids.monads

import cats._
import cats.data._
import cats.effect._

object MinimalMonadTransformer extends IOApp {

  type Result[A] = StateT[IO, Int, A]

  def increment: Result[Unit] = StateT.modify[IO, Int](_ + 1)

  def greet(name: String): Result[String] = for {
    _ <- increment
    msg <- StateT.liftF(IO(s"Hello, $name!"))
  } yield msg

  def run(args: List[String]): IO[ExitCode] = for {
    result <- greet("World").run(0)
    (count, message) = result
    _ <- IO(println(s"$message (operations: $count)"))
  } yield ExitCode.Success
}