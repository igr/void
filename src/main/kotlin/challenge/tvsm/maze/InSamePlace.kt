package challenge.tvsm.maze

import challenge.tvsm.ctx.TwoHeroes

object InSamePlace: (TwoHeroes, (TwoHeroes) -> Unit) -> TwoHeroes {
	override fun invoke(twoHeroes: TwoHeroes, fn: (TwoHeroes) -> Unit): TwoHeroes {
		if (twoHeroes.theseus.samePlaceAs(twoHeroes.minotaur)) {
			fn.invoke(twoHeroes)
		}
		return twoHeroes
	}

}