package voids.fn

object FunctorExample {

  /**
   * Functor for Option.
   */
  implicit object OptionFunctor extends Functor[Option] {
    def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa match {
      case Some(r) => Some(f(r))
      case None => None
    }
  }


  def main(args: Array[String]): Unit = {
    val a = Some(2)

    val b = OptionFunctor.map(a)(a => a + 1)
    println(b)

    // using implicit
    val c = a map (a => a + 2)
    println(c)
  }
}
