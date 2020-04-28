package com.ynap.foo

trait Foofy[A] {
  def foo(a: A): String
}

object Foofy {
  def apply[A](implicit instance: Foofy[A]): Foofy[A] = instance
}

object instances {
  object foofy {
    implicit object FooInt extends Foofy[Int] {
      override def foo(a: Int): String =
        s"foo-$a"
    }

    implicit object FooString extends Foofy[String] {
      override def foo(a: String): String =
        s"foo-$a"
    }
  }
}

object syntax {
  object foofy {
    implicit class toFoofy[A](target: A)(implicit instance: Foofy[A]) {
      def foo(): String = instance.foo(target)
    }
  }
}
