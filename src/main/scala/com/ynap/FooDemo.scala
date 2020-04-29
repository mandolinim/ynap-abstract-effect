package com.ynap

object FooDemo {
  // typeclass
  import com.ynap.foo.Foofy
  // typeclass instance
  import com.ynap.foo.instances.foofy._
  // syntax
  import com.ynap.foo.Foofy.ops._

  def demo1[A: Foofy](a: A): String =
    Foofy[A].foo(a)

  def demo2[A: Foofy](a: A): String =
    a.foo

  def run(): Unit = {
    println()
    println("**************************************************")
    println("***************** Foofy **************************")
    println()

    println("Foo: " + demo1(42))
    println("Foo: " + demo2("ciao"))
  }

}
