package com.ynap

import scala.util.Try

object AbstractMonad {

  // typeclass
  import cats.Applicative

  // syntax for pure
  import cats.syntax.applicative._

  case class ItemId(value: Int)
  case class Item(id: ItemId, qty: Int)

  def load[F[_]: Applicative](id: ItemId): F[Item] =
    Item(id, 100).pure

  def save[F[_]: Applicative](item: Item): F[Unit] =
    ().pure

  def checkIn(qty: Int, item: Item): Item =
    item.copy(qty = item.qty + qty)

  // typeclass
  import cats.Monad

  // syntax for map
  import cats.syntax.functor._
  // syntax for flatMap
  import cats.syntax.flatMap._

  def program[F[_]: Monad]: F[Unit] =
    load[F](ItemId(52))
      .map(item => checkIn(10, item))
      .flatMap(updated => save[F](updated))

  def program2[F[_]: Monad]: F[Unit] =
    for {
      item    <- load[F](ItemId(42))
      updated = checkIn(10, item)
      _       <- save[F](updated)
    } yield ()

  // run the computations
  def run(): Unit = {
    // typeclass instances for Try
    import cats.instances.try_._
    println("Abstract w/ Try: " + program[Try].fold("error " + _.getMessage, _ => "ok"))

    // typeclass instances for IO
    import cats.effect.IO
    program[IO].unsafeRunSync()
    println("Abstract w/ IO: ok")

    // typeclass instances for Option
    import cats.instances.option._
    println("Abstract w/ Option: " + program[Option].fold("error")(_ => "ok"))
  }
}
