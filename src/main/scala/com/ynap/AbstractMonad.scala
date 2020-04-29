package com.ynap

object AbstractMonad {

  // typeclasses
  import cats.{ Applicative, Monad, MonadError }

  type Error = String

  // verbose F constraints w/out kind-projector support
  // def createItem[F[_]](id: Int, qty: String)(implicit ME: MonadError[F, Error]): F[Item] = {
  // compact F constraints w kind-projector support
  def createItem[F[_]: MonadError[*[_], String]](id: Int, qty: String): F[Item] = {
    import cats.implicits._

    Either
      .catchNonFatal(Item(ItemId(id), qty.toInt))
      .leftMap(t => s"qty isn't an int (${t.getMessage})")
      .liftTo[F]
  }

  def load[F[_]: Applicative](id: ItemId): F[Item] = {
    import cats.syntax.applicative._

    Item(id, 100).pure
  }

  def save[F[_]: Applicative](item: Item): F[Unit] = {
    import cats.syntax.applicative._

    ().pure
  }

  // verbose F constraints w/out kind-projector support
  //  def program[F[_]](implicit M: Monad[F]): F[Unit] = {
  // compact F constraints w kind-projector support
  def program[F[_]: Monad]: F[Unit] = {
    import cats.syntax.flatMap._
    import cats.syntax.functor._

    for {
      item    <- load[F](ItemId(42))
      updated = item.checkIn(10)
      _       <- save[F](updated)
    } yield ()
  }

  // verbose F constraints w/out kind-projector support
  // def programBad[F[_]](implicit M: Monad[F], ME: MonadError[F, Error]): F[Unit] = {
  // compact F constraints w kind-projector support
  def programBad[F[_]: Monad: MonadError[*[_], Error]]: F[Unit] = {
    import cats.syntax.flatMap._
    import cats.syntax.functor._

    for {
      item <- createItem[F](42, "ASD")
      _    <- save[F](item)
    } yield ()
  }

  def run(): Unit = {
    println()
    println("**************************************************")
    println("***************** Abstract ***********************")
    println()

    runEither()
    runTry()
    runOption()
    runIO()
  }

  def runEither(): Unit = {
    import cats.instances.either._

    val result = program[Either[String, *]].fold("error " + _, _ => "ok")
    println("Either: " + result)

    val resultBad = programBad[Either[String, *]].fold("error " + _, _ => "ok")
    println("Either (bad program): " + resultBad)
  }

  def runTry(): Unit = {
    import cats.instances.try_._

    import scala.util.Try

    val result = program[Try].fold("error " + _.getMessage, _ => "ok")
    println("Try: " + result)
  }

  def runOption(): Unit = {
    import cats.instances.option._

    val result = program[Option].fold("error")(_ => "ok")
    println("Option: " + result)
  }

  def runIO(): Unit = {
    import cats.effect.IO

    program[IO].unsafeRunSync()
    println("IO: ok")
  }
}
