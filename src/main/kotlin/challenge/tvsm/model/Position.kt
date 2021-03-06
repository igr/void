package challenge.tvsm.model

interface PositionK {
	fun samePositionAs(other: PositionK): Boolean
}

// ** Positions creation should not come out of this file
// ** Exception: the maze initialization

class PositionInPassageK(
	val passage: PassageK,
	val direction: CavernK,
	val enteredFrom: CavernK?
) : PositionK {

	fun changeDirection() : PositionK {
		val newDirection = passage.otherEndOf(direction)
		return PositionInPassageK(passage, newDirection, null)
	}

	fun stepIntoCavern(): PositionK {
		return PositionInCavernK(direction, passage)
	}

	override fun samePositionAs(other: PositionK): Boolean {
		if (other !is PositionInPassageK) {
			return false
		}
		return this.passage == other.passage
	}

	override fun toString(): String {
		return "Passage: $passage direction: $direction"
	}
}

class PositionInCavernK(
	val cavern: CavernK,
	val previousPassage: PassageK
) : PositionK {

	override fun samePositionAs(other: PositionK): Boolean {
		if (other !is PositionInCavernK) {
			return false
		}
		return this.cavern == other.cavern
	}
	override fun toString(): String {
		return "Cavern: $cavern from: $previousPassage"
	}

	fun enterPassage(pp: PassageK): PositionInPassageK {
		return PositionInPassageK(pp, pp.otherEndOf(cavern), cavern)
	}
}

