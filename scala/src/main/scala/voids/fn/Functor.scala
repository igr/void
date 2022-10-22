package voids.fn

// The F in Functor is often referred to as an "effect" or "computational context."
trait Functor[F[_]] {
  /**
   * map allows us to apply a plain function to a value which is contained in a context,
   * or equivalently, it "lifts" a function on values to the level of a
   * function on contexts holding values.
   */
  def map[A, B](fa: F[A])(f: A => B): F[B]
}