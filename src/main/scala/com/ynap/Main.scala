package com.ynap

import cats.effect.IO

import scala.util.Try

object Main extends App {

  OptionMonad.run()
  TryMonad.run()
  EitherMonad.run()
  IOMonad.run()

  AbstractMonad.run()

  FooDemo.run()

  println("done")
}

object Playground {
  import cats.implicits._

  type MyOption[A] = Either[Unit, A]
  type MyTry[A]    = Either[Throwable, A]
  type MyIO[A]    = Either[Throwable, A]

  def convert() = {
    val a: Either[String, Int]    = 42.asRight[String]
    val a1: Either[String, Int]    = "errore".asLeft[Int]
    val b: Either[String, String] = a.map(_.toString)
    val c: Either[Int, String]    = b.leftMap(_.toInt)
    val d: Either[Int, String] = c.handleError {
      case 42 => "ciao"
      case _ => "hello"
    }


  }

}
