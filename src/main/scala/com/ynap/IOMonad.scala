package com.ynap

import cats.effect.IO

object IOMonad {

  def createItem(id: Int, qty: String): IO[Item] =
    IO {
      Item(ItemId(id), qty.toInt)
    }

  def load(id: ItemId): IO[Item] =
    IO.apply(Item(id, 100))

  def save(item: Item): IO[Unit] =
    IO.unit

  val program: IO[Unit] =
    for {
      item    <- load(ItemId(42))
      updated = item.checkIn(10)
      _       <- save(updated)
    } yield ()

  def run(): Unit = {
    program.unsafeRunSync()
    println("IO: ok")
  }

}
