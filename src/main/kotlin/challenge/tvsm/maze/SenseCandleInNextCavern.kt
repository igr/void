package challenge.tvsm.maze

import challenge.tvsm.HeroAndCandles
import challenge.tvsm.model.HeroK

class CandleSense(private val hero: HeroK, private val candleDetected: Boolean) {
	fun <T> ifCandleSeen(body: (HeroK) -> T): Unit {
		if (candleDetected) {
			body.invoke(hero)
		}
	}
}

val senseCandleInNextCavern = fun(heroWithCandles: HeroAndCandles): CandleSense {
	with(heroWithCandles) {
		val senseCandle: Boolean = hero.doInPassage {
			candles.hasCandleIn(it.direction)
		}
		return CandleSense(hero, senseCandle)
	}
}
