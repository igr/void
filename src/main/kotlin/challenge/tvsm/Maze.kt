package challenge.tvsm

import challenge.tvsm.ctx.HeroAndCandles
import challenge.tvsm.ctx.TwoHeroes
import challenge.tvsm.maze.*
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

	fun solve() {
		var theend = false;

		while (true) {

			senseCandleInNextCavern(HeroAndCandles(minotaur, candles))
				.ifCandleSeen {
					turnBack(it)
				}

			minotaur(enterCavern)
			theseus(enterCavern)

			putCandleInHeroesCavern(HeroAndCandles(theseus, candles))

			heroes {
				inSamePlace(it) {
					TheseusKillMinotaur(it)
					theend = true
				}
			}

			if (theend) break

			minotaur(EnterLeftUnmarkedExit)
			theseus(EnterRightUnmarkedExit)

			heroes {
				inSamePlace(it) {
					MinotaurKillTheseus(it)
					theend = true
				}
			}

			if (theend) break
		}
	}


}