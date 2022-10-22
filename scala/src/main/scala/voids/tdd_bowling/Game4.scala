package voids.tdd_bowling

class Game4 extends Game {

  private val rolls = new Array[Int](21)
  private var currentRoll: Int = 0

  override def roll(pins: Int): Unit = {
    rolls(currentRoll) = pins
    currentRoll += 1
  }

  override def score(): Int = {
    var score = 0
    var rollNdx = 0

    while (rollNdx < rolls.length) {
      val roll = rolls(rollNdx)

      var scoreForRoll = roll

      if (roll == 10) { // strike
        scoreForRoll += rolls(rollNdx + 1)
        scoreForRoll += rolls(rollNdx + 2)
      }

      score += scoreForRoll

      rollNdx += 1
    }
    score
  }
}
