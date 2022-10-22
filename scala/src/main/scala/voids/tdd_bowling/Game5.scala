package voids.tdd_bowling

class Game5 extends Game {

  private val rolls = new Array[Int](21)
  private var currentRoll: Int = 0

  override def roll(pins: Int): Unit = {
    rolls(currentRoll) = pins
    currentRoll += 1
  }

  override def score(): Int = {
    var score = 0
    var rollNdx = 0

    while (rollNdx < 20) {    // FIX!
      val roll = rolls(rollNdx)

      var frameScore = roll     // rename

      if (roll == 10) { // strike
        frameScore += rolls(rollNdx + 1)
        frameScore += rolls(rollNdx + 2)
      }
      else if (roll + rolls(rollNdx + 1) == 10) { // spare
        frameScore += rolls(rollNdx + 1)
        frameScore += rolls(rollNdx + 2)

        rollNdx += 1  // consuming two rolls at once
      }

      score += frameScore

      rollNdx += 1
    }
    score
  }
}
