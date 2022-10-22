package voids.fn

object MonadExample {

  implicit object OptionMonad extends Monad[Option] {

    override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = {
      if (fa.isEmpty) {
        return Option.empty
      }
      f(fa.get)
    }

    // vvv --- applicative --- vvv

    override def pure[A](a: A): Option[A] = Option.apply(a)

    override def ap[A, B](fa: Option[A])(ff: Option[A => B]): Option[B] = {
      if ((ff.isEmpty) || (fa.isEmpty)) {
        return Option.empty
      }
      Some(ff.get(fa.get))
    }
  }

  def main(args: Array[String]): Unit = {
    val opt1 = OptionMonad.pure(3)

    val opt2 = OptionMonad.flatMap(opt1)(a => Some(a + 2))
    println(opt2) // Some(5)

    val opt3 = OptionMonad.map(opt2)(a => 100 * a)
    println(opt3) // Some(500)
  }

}
