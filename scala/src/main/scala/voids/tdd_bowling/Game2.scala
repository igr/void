package voids.tdd_bowling

class Game2 extends Game {

  private var pins: Int = 0

  override def roll(pins: Int): Unit = {
    this.pins += pins
  }

  override def score(): Int = {
    if (this.pins > 0) {
      return 1
    }
    0
  }
}
