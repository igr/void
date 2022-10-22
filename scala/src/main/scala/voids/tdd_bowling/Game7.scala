package voids.tdd_bowling

class Game7 extends Game {

  private val rolls = new Array[Int](21)
  private var currentRoll: Int = 0

  override def roll(pins: Int): Unit = {
    rolls(currentRoll) = pins
    currentRoll += 1
  }

  override def score(): Int = {
    var score = 0
    var rollNdx = 0
    var frame = 0

    while (playingFrame(frame)) {
      var frameScore = simpleFrameScore(rollNdx)

      if (isStrike(rollNdx)) {
        frameScore += rolls(rollNdx + 2)
        rollNdx += 1
      }
      else if (isSpare(rollNdx)) {
        frameScore += rolls(rollNdx + 2)
        rollNdx += 2
      }
      else {
        rollNdx += 2
      }

      score += frameScore
      frame += 1
    }
    score
  }

  private def playingFrame(frame: Int): Boolean = {
    frame < 10
  }

  private def isStrike(rollNdx: Int): Boolean = {
    rolls(rollNdx) == 10
  }
  private def isSpare(rollNdx: Int): Boolean = {
    rolls(rollNdx) + rolls(rollNdx + 1) == 10
  }
  private def simpleFrameScore(rollNdx: Int): Int = {
    rolls(rollNdx) + rolls(rollNdx + 1)
  }


}
