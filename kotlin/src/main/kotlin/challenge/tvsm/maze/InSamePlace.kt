package challenge.tvsm.maze

import challenge.tvsm.TwoHeroes

class SamePlace(private val twoHeroes: TwoHeroes, private val same: Boolean) {
	fun inSamePlace(fn: (TwoHeroes) -> Unit): Boolean {
		if (same) {
			fn(twoHeroes)
		}
		return same
	}
}

val checkPlace = fun(twoHeroes: TwoHeroes): SamePlace {
	with(twoHeroes) {
		return SamePlace(twoHeroes, theseus.samePlaceAs(minotaur))
	}
}