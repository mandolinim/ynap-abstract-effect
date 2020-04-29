package com.ynap.foo

import simulacrum._

@typeclass trait Foofy[A] {
  def foo(a: A): String
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
