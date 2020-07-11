package challenge.tvsm.maze

import challenge.tvsm.ctx.HeroAndCandles

class CandleSense(private val candleDetected: Boolean) {
	fun ifCandleSeen(body: () -> Unit) {
		if (candleDetected) {
			body.invoke()
		}
	}
}

val senseCandleInNextCavern = fun(heroWithCandles: HeroAndCandles): CandleSense {
	with(heroWithCandles) {
		val senseCandle: Boolean = hero.doInPassage {
			candles.hasCandleIn(it.direction)
		}
		return CandleSense(senseCandle)
	}
}
