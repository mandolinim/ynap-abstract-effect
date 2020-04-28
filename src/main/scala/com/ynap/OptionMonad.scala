package com.ynap

object OptionMonad {

  def createItem(id: Int, qty: String): Option[Item] =
    if (qty.matches("^[0-9]+$")) Some(Item(ItemId(id), qty.toInt))
    else None

  def load(id: ItemId): Option[Item] =
    Option.apply(Item(id, 100))

  def save(item: Item): Option[Unit] =
    Option(())

  def checkIn(qty: Int, item: Item): Item =
    item.copy(qty = item.qty + qty)

  val program: Option[Unit] =
    for {
      item    <- load(ItemId(42))
      updated = checkIn(10, item)
      _       <- save(updated)
    } yield ()

  // run the computation
  def run(): Unit =
    println("Option: " + program.fold("error")(_ => "ok "))
}
