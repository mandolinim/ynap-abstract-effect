package com.ynap

import scala.util.Try

import cats._
import cats.implicits._
import cats.effect._

object Playground {

  type MyOption[A] = Either[Unit, A]
  type MyTry[A]    = Either[Throwable, A]
  type MyIO[A]     = Either[Throwable, A]

  def convert(): Unit = {
    val a: Either[String, Int]    = 42.asRight[String]
    val a1: Either[String, Int]   = "errore".asLeft[Int]
    val b: Either[String, String] = a.map(_.toString)
    val c: Either[Int, String]    = b.leftMap(_.toInt)
    val d: Either[Int, String] = c.handleError {
      case 42 => "ciao"
      case _  => "hello"
    }
  }

  val OptionME = MonadError[Option, Unit]
  val TryME    = MonadError[Try, Throwable]
  val IoME     = MonadError[IO, Throwable]

  type ErrorIntOr[A] = Either[Int, A]

  def EitherMEString = MonadError[Either[String, *], String]
  def EitherME[E]    = MonadError[Either[E, *], E]
  val EitherIntME    = MonadError[ErrorIntOr, Int]
}
