package challenge.tvsm.model

abstract class HeroK(val name: String, p: PositionK) {
	private val trail = mutableListOf<PositionK>()
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

	fun samePlaceAs(other: HeroK) : Boolean {
		return this.position.samePositionAs(other.position)
	}

	fun move(newPosition: PositionK): HeroK {
		trail.add(position)
		position = newPosition

		println("$name in $position")
		return this
	}

	fun <T> doInPassage(fn: (PositionInPassageK) -> T): T {
		if (position !is PositionInPassageK) {
			throw IllegalStateException("$name not in a passage!")
		}
		return fn(position as PositionInPassageK)
	}

	fun <T> doInCavern(consumer: (PositionInCavernK) -> T) : T {
		if (position !is PositionInCavernK) {
			throw IllegalStateException("$name not in a cavern!")
		}
		return consumer(position as PositionInCavernK)
	}
}

class HeroMinotaurK(position: PositionK) : HeroK("Minotaur", position)
class HeroTheseusK(position: PositionK) : HeroK("Theseus", position)
