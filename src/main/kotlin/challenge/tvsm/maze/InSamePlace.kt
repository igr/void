package challenge.tvsm.maze

import challenge.tvsm.ctx.TwoHeroes

val inSamePlace = fun(twoHeroes: TwoHeroes, fn: (TwoHeroes) -> Unit): TwoHeroes {
	with(twoHeroes) {
		if (theseus.samePlaceAs(minotaur)) {
			fn.invoke(twoHeroes)
		}
		return twoHeroes
	}
}