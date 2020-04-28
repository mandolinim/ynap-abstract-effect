package com.ynap

object OptionMonad {

  def createItem(id: Int, qty: String): Option[Item] =
    if (qty.matches("^[0-9]+$")) Some(Item(ItemId(id), qty.toInt))
    else None

  def load(id: ItemId): Option[Item] =
    Option.apply(Item(id, 100))

  def save(item: Item): Option[Unit] =
    Option(())

  val program: Option[Unit] =
    for {
      item    <- load(ItemId(42))
      updated = item.checkIn(10)
      _       <- save(updated)
    } yield ()

  def run(): Unit =
    println("Option: " + program.fold("error")(_ => "ok "))
}
