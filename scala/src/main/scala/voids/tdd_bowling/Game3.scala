package voids.tdd_bowling

class Game3 extends Game {

  private var pins: Int = 0

  override def roll(pins: Int): Unit = {
    this.pins += pins
  }

  override def score(): Int = {
    this.pins
  }
}
