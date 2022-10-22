package voids.tdd_bowling

import org.scalatest.funsuite.AnyFunSuite

class GameTests extends AnyFunSuite:

  def game() = {
    new Game7
  }

  test("0") {
    val g = game()

    rollMany(g, 20, 0)

    assert(g.score() == 0)
  }

  test("1") {
    val g = game()

    rollMany(g, 19, 0)
    g.roll(1)

    assert(g.score() == 1)
  }

  test("3_weak") {
    val g = game()

    g.roll(5)
    g.roll(3)
    rollMany(g, 18, 0)

    assert(g.score() == 8)
  }

  test("4_strike") {
    val g = game()

    g.roll(10)
    g.roll(4)
    g.roll(3)
    rollMany(g, 16, 0)

    assert(g.score() == 17 + 7)
  }

  test("5_spare") {
    val g = game()

    g.roll(6)
    g.roll(4)
    g.roll(3)
    rollMany(g, 17, 0)

    assert(g.score() == 13 + 3)
  }

  test("6_perfectGame") {
    val g = game()

    rollMany(g, 12, 10)

    assert(g.score() == 300)
  }

  private def rollMany(g: Game, count: Int, pins: Int): Unit = {
    for (_ <- 0 until count) {
      g.roll(pins)
    }
  }

end GameTests
