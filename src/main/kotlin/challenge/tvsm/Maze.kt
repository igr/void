package challenge.tvsm

import challenge.tvsm.ctx.TwoHeroes
import challenge.tvsm.model.Candles
import challenge.tvsm.model.HeroK
import challenge.tvsm.model.HeroMinotaurK
import challenge.tvsm.model.HeroTheseusK

class MazeK(
	var theseus: HeroTheseusK,
	var minotaur: HeroMinotaurK,
	var candles: Candles = Candles()
) {

	fun minotaur(fn: (HeroMinotaurK) -> Unit): MazeK {
		fn(minotaur)
		return this
	}

	fun theseus(fn: (HeroK) -> Unit): MazeK {
		fn(theseus)
		return this
	}

	fun heroes(fn: (TwoHeroes) -> Unit): MazeK {
		fn.invoke(TwoHeroes(theseus, minotaur))
		return this
	}

}