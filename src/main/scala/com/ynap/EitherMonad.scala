package com.ynap

object EitherMonad {

  type Error = String

  def createItem(id: Int, qty: String): Either[Error, Item] =
    if (qty.matches("^[0-9]+$")) Right(Item(ItemId(id), qty.toInt))
    else Left("qty not an int")

  def load(id: ItemId): Either[Error, Item] =
    Right(Item(id, 100))

  def save(item: Item): Either[Error, Unit] =
    Right(())

  def checkIn(qty: Int, item: Item): Item =
    item.copy(qty = item.qty + qty)

  val program: Either[Error, Unit] =
    for {
      item    <- load(ItemId(42))
      updated = checkIn(10, item)
      _       <- save(updated)
    } yield ()

  // run the computation
  def run(): Unit =
    println("Either: " + program.fold("error " + _, _ => "ok"))

}
