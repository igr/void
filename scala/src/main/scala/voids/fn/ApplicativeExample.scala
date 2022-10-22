package voids.fn

object ApplicativeExample {

  implicit object OptionApplicative extends Applicative[Option] {

    override def pure[A](a: A): Option[A] = Option.apply(a)

    override def ap[A, B](fa: Option[A])(ff: Option[A => B]): Option[B] = {
      // we have 2x2 variants here!
      // however, only one variant returns Some

      if ((ff.isEmpty) || (fa.isEmpty)) {
        return Option.empty
      }
      Some(ff.get(fa.get))
    }
  }

  implicit object SetApplicative extends Applicative[Set] {
    override def pure[A](a: A): Set[A] = Set(a)

    override def ap[A, B](fa: Set[A])(ff: Set[A => B]): Set[B] = {
      var result = Set[B]()

      ff.foreach(fn => {
        fa.foreach(a => {
          result += fn(a)
        })
      })

      result
    }
  }


  def main(args: Array[String]): Unit = {
    val a = Some(2)
    val ff = Some((x: Int) => x + 1)

    val b = OptionApplicative.ap(a)(ff)
    println(b)

    // --------------------

    val s = Set(1, 2, 3)
    val ff2 = Set((x: Int) => x + 1, (x: Int) => x - 1)

    val c = SetApplicative.ap(s)(ff2)

    // 0 1 2 3 4
    println(c)
  }
}
