package com.ynap

object Main extends App {

  OptionMonad.run()
  TryMonad.run()
  EitherMonad.run()
  IOMonad.run()
  FooDemo.run()
  AbstractMonad.run()

  println("done")
}
