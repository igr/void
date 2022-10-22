package challenge.tvsm.model

interface CavernK {
	val name: String
	fun rightPassageOf(passage: PassageK): PassageK
	fun leftPassageOf(passage: PassageK): PassageK
}

data class MutableCavernK(override val name: String) : CavernK {
	private val passages = mutableListOf<PassageK>()

	fun addPassage(passage: PassageK) {
		if (passage !in passages) {
			passages.add(passage)
		}
	}

	override fun rightPassageOf(passage: PassageK): PassageK {
		var ndx = passages.indexOf(passage)
		if (ndx == -1) {
			throw IllegalStateException("Passage not found")
		}
		ndx++
		if (ndx == passages.size) {
			ndx = 0
		}
		return passages[ndx]
	}

	override fun leftPassageOf(passage: PassageK): PassageK {
		var ndx = passages.indexOf(passage)
		if (ndx == -1) {
			throw IllegalStateException("Passage not found")
		}
		ndx--
		if (ndx == -1) {
			ndx = passages.size - 1
		}
		return passages[ndx]
	}

	override fun toString(): String {
		return "[$name]"
	}

	operator fun compareTo(other: MutableCavernK): Int {
		return this.name.compareTo(other.name)
	}
}
