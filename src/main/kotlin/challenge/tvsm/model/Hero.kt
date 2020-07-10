package challenge.tvsm.model

abstract class HeroK(val name: String, p: PositionK) {
	private var trail = mutableListOf<PositionK>()
	private var position: PositionK = p

	init {
		move(p)
	}

	fun usedExitTo(exitTo: CavernK): Boolean {
		if (position !is PositionInCavernK) {
			throw IllegalStateException("$name not in a cavern!")
		}
		return trail
			.filterIsInstance<PositionInPassageK>()
			.filter { it.enteredFrom == this.position }
			.filter { it.direction == exitTo }
			.any()
	}

	private fun move(newPosition: PositionK) {
		trail.add(position)
		position = newPosition

		println("$name in $position")
	}

	fun moveInPassage(fn: (PositionInPassageK) -> PositionK) {
		if (position !is PositionInPassageK) {
			throw IllegalStateException("$name not in a passage!")
		}
		val position = fn(position as PositionInPassageK)
		return move(position)
	}

	fun enterThePassage(pp: PassageK) {
		if (position !is PositionInCavernK) {
			throw IllegalStateException("$name not in a cavern!")
		}
		val cavernFrom = (position as PositionInCavernK).cavern
		move(PositionInPassageK(pp, pp.otherEndOf(cavernFrom), cavernFrom))
	}

	fun <T> doInPassage(consumer: (PositionInPassageK) -> T): T {
		if (position !is PositionInPassageK) {
			throw IllegalStateException("$name not in a passage!")
		}
		return consumer(position as PositionInPassageK)
	}

	fun <T> doInCavern(consumer: (PositionInCavernK) -> T) : T {
		if (position !is PositionInCavernK) {
			throw IllegalStateException("$name not in a cavern!")
		}
		return consumer(position as PositionInCavernK)
	}

	fun samePlaceAs(other: HeroK) : Boolean {
		return this.position.samePositionAs(other.position)
	}

}

class HeroMinotaurK(position: PositionK) : HeroK("Minotaur", position)
class HeroTheseusK(position: PositionK) : HeroK("Theseus", position)
