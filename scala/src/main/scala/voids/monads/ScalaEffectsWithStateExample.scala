package voids.monads

import cats._
import cats.data._
import cats.effect._
import cats.implicits._

object ScalaEffectsWithStateExample extends IOApp {

  sealed trait AppError
  case class ValidationError(msg: String) extends AppError
  case class DatabaseError(msg: String) extends AppError

  // State to track operations
  case class AppState(operationCount: Int, auditLog: List[String])

  // Type lambda for EitherT[IO, AppError, *]
  type ErrorIO[A] = EitherT[IO, AppError, A]

  // Combining IO, Either, and State using monad transformers
  type Result[A] = StateT[ErrorIO, AppState, A]

  // Helper to lift IO + Either into our Result type
  def liftResult[A](eitherT: EitherT[IO, AppError, A]): Result[A] = {
    StateT.liftF(eitherT)
  }

  // Helper to record an operation
  def recordOperation(operation: String): Result[Unit] = {
    StateT.modify[ErrorIO, AppState] { state =>
      state.copy(
        operationCount = state.operationCount + 1,
        auditLog = operation :: state.auditLog
      )
    }
  }

  // Simulated database operation that can fail
  def fetchUser(id: Int): Result[String] = for {
    _ <- recordOperation(s"fetchUser($id)")
    user <- liftResult {
      if (id > 0) {
        EitherT.right(IO {
          println(s"Fetching user $id from database...")
          s"User_$id"
        })
      } else {
        EitherT.left(IO.pure(ValidationError("User ID must be positive")))
      }
    }
  } yield user

  // Simulated validation that can fail
  def validateUser(name: String): Result[String] = for {
    _ <- recordOperation(s"validateUser($name)")
    validated <- liftResult {
      if (name.startsWith("User_")) {
        EitherT.right(IO {
          println(s"Validating $name...")
          name
        })
      } else {
        EitherT.left(IO.pure(ValidationError(s"Invalid user name: $name")))
      }
    }
  } yield validated

  // Simulated logging operation
  def logUser(name: String): Result[Unit] = for {
    _ <- recordOperation(s"logUser($name)")
    _ <- liftResult {
      EitherT.right(IO {
        println(s"Logging: Successfully processed $name")
      })
    }
  } yield ()

  // Get current state
  def getState: Result[AppState] = {
    StateT.get[ErrorIO, AppState]
  }

  // Combining all effects using for-comprehension
  def processUser(id: Int): Result[String] = for {
    user <- fetchUser(id)
    validated <- validateUser(user)
    _ <- logUser(validated)
    state <- getState
    _ <- liftResult(EitherT.right(IO {
      println(s"\n--- State Summary ---")
      println(s"Total operations: ${state.operationCount}")
      println(s"Audit log: ${state.auditLog.reverse.mkString(", ")}")
    }))
  } yield validated

  def run(args: List[String]): IO[ExitCode] = {
    val initialState = AppState(0, List.empty)

    // Run the StateT to get EitherT[IO, AppError, (AppState, String)]
    val result = processUser(42).run(initialState).value

    result.flatMap {
      case Right((finalState, user)) =>
        IO {
          println(s"\nSuccess: $user")
          println(s"Final operation count: ${finalState.operationCount}")
        }.as(ExitCode.Success)
      case Left(error) =>
        IO(println(s"\nError: $error")).as(ExitCode.Error)
    }
  }
}