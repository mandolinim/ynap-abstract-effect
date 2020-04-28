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

  def checkIn(qty: Int, item: Item): Item =
    item.copy(qty = item.qty + qty)

  val program: Try[Unit] =
    for {
      item    <- load(ItemId(42))
      updated = checkIn(10, item)
      _       <- save(updated)
    } yield ()

  // run the computation
  def run(): Unit =
    println("Try: " + program.fold("error " + _.getMessage, _ => "ok"))

}
