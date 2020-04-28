package com.ynap

case class ItemId(value: Int)

case class Item(id: ItemId, qty: Int) {
  def checkIn(qty: Int): Item =
    copy(qty = qty + qty)
}
