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

  val program: Either[Error, Unit] =
    for {
      item    <- load(ItemId(42))
      updated = item.checkIn(10)
      _       <- save(updated)
    } yield ()

  val programBad: Either[Error, Unit] =
    for {
      item <- createItem(42, "ASD")
      _    <- save(item)
    } yield ()

  def run(): Unit = {
    println()
    println("**************************************************")
    println("***************** Either *************************")
    println()

    val result = program.fold("error " + _, _ => "ok")
    println("Either: " + result)

    val resultBad = programBad.fold("error " + _, _ => "ok")
    println("Either (bad program): " + resultBad)
  }

}
