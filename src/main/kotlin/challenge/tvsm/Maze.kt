package challenge.tvsm

import challenge.tvsm.maze.*
import challenge.tvsm.model.Candles
import challenge.tvsm.model.HeroK
import challenge.tvsm.model.HeroMinotaurK
import challenge.tvsm.model.HeroTheseusK

class TwoHeroes(val theseus: HeroTheseusK, val minotaur: HeroMinotaurK)
class HeroAndCandles(val hero: HeroK, val candles: Candles)

class MazeK(
	private var theseus: HeroTheseusK,
	private var minotaur: HeroMinotaurK,
	private var candles: Candles = Candles()
) {

	fun minotaur(fn: (HeroMinotaurK) -> Unit) {
		fn(minotaur)
	}

	fun theseus(fn: (HeroK) -> Unit) {
		fn(theseus)
	}

	private fun heroes(): TwoHeroes  {
		return TwoHeroes(theseus, minotaur)
	}

	fun solve() {
		while (true) {

			senseCandleInNextCavern(HeroAndCandles(minotaur, candles))
				.ifCandleSeen {
					turnBack(it)
				}

			minotaur(enterCavern)
			theseus(enterCavern)

			HeroAndCandles(theseus, candles).apply {
				putCandleInHeroesCavern(this)
			}

			checkPlace(heroes())
				.inSamePlace {
					TheseusKillMinotaur(it)
				}
				.also { if (it) return@solve }

			minotaur(enterLeftUnmarkedExit)
			theseus(enterRightUnmarkedExit)

			checkPlace(heroes())
				.inSamePlace {
					MinotaurKillTheseus(it)
				}
				.also { if (it) return@solve }
		}
	}


}