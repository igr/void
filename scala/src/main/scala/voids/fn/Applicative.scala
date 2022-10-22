package voids.fn

// they allow sequencing of functorial computations (unlike plain functors)
// but without deciding on which computation to perform on the basis of the
// result of a previous computation (unlike monads).

trait Applicative[F[_]] extends Apply[F] {
  /**
   * Simply places a value into a minimal context.
   */
  def pure[A](a: A): F[A]

  /**
   * map is now defined. first, we place function into the same context
   * and then apply it to the context.
   */
  def map[A, B](fa: F[A])(f: A => B): F[B] = ap(fa)(pure(f))
}