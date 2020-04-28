package com.ynap

import scala.util._

object TryMonad {

  def createItem(id: Int, qty: String): Try[Item] =
    Try {
      Item(ItemId(id), qty.toInt)
    }

  def load(id: ItemId): Try[Item] =
    Try(Item(id, 100))

  def save(item: Item): Try[Unit] =
    Try(())

  val program: Try[Unit] =
    for {
      item    <- load(ItemId(42))
      updated = item.checkIn(10)
      _       <- save(updated)
    } yield ()

  def run(): Unit =
    println("Try: " + program.fold("error " + _.getMessage, _ => "ok"))

}
