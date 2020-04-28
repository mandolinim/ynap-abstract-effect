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

  def checkIn(qty: Int, item: Item): Item =
    item.copy(qty = item.qty + qty)

  val program: IO[Unit] =
    for {
      item    <- load(ItemId(42))
      updated = checkIn(10, item)
      _       <- save(updated)
    } yield ()

  // run the computation
  def run(): Unit = {
    program.unsafeRunSync()
    println("IO: ok")
  }

}
