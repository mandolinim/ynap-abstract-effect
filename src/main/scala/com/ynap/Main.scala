package com.ynap

object Main extends App {

  OptionMonad.run()
  TryMonad.run()
  EitherMonad.run()
  IOMonad.run()
  AbstractMonad.run()

  FooDemo.run()

  println("done")
}
