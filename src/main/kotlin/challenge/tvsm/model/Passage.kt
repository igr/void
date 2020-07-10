package challenge.tvsm.model

interface PassageK {
	val a: CavernK
	val b: CavernK

	fun otherEndOf(x : CavernK): CavernK {
		return when (x) {
			a -> b
			b -> a
			else -> throw IllegalStateException("Cavern $x not connected by the passage")
		}
	}
}

class MutablePassageK(override val a: MutableCavernK, override val b: MutableCavernK) : PassageK {

	fun connectCaverns() {
		a.addPassage(this)
		b.addPassage(this)
	}

	override fun toString(): String {
		return "$a-$b"
	}

	override fun equals(other: Any?): Boolean {
		if (other == null) {
			return false
		}
		if (other !is MutablePassageK) {
			return false
		}
		return ((this.a == other.a) && (this.b == other.b)) ||
			((this.a == other.b) && (this.b == other.a))
	}

	// very important to produce the same hash code in both cases [A,B] and [B,A]
	override fun hashCode(): Int {
		var a1 = a
		var b1 = b

		if (a > b) {
			a1 = b
			b1 = a
		}

		return 31 * a1.hashCode() + b1.hashCode()
	}
}
