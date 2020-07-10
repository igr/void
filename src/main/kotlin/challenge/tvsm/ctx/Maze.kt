package challenge.tvsm.ctx

import challenge.tvsm.model.Candles
import challenge.tvsm.model.HeroK
import challenge.tvsm.model.HeroMinotaurK
import challenge.tvsm.model.HeroTheseusK

class MazeK(
	private var theseus: HeroTheseusK,
	private var minotaur: HeroMinotaurK
) {

	private val candles = Candles

	fun minotaur(fn: (HeroK) -> Unit): MazeK {
		fn(minotaur)
		return this
	}

	fun minotaurAndCandles(fn: (HeroAndCandles) -> Unit): MazeK {
		fn.invoke(HeroAndCandles(minotaur, candles))
		return this
	}

	fun theseus(fn: (HeroK) -> Unit): MazeK {
		fn(theseus)
		return this
	}

	fun thesesAndCandles(fn: (HeroAndCandles) -> Unit): MazeK {
		fn.invoke(HeroAndCandles(theseus, candles))
		return this
	}

	fun heroes(fn: (TwoHeroes) -> Unit): MazeK {
		fn.invoke(TwoHeroes(theseus, minotaur))
		return this
	}


}