package challenge.tvsm.maze

import challenge.tvsm.ctx.HeroAndCandles
import challenge.tvsm.model.HeroK

class HeroWithCandleSense(private val hero: HeroK, private val has: Boolean) {
	fun ifCandleSeen(map: (HeroK) -> Unit) {
		if (has) {
			map.invoke(hero)
		}
	}
}

object SenseCandleInNextCavern: (HeroAndCandles) -> HeroWithCandleSense {
	override fun invoke(heroWithCandles: HeroAndCandles): HeroWithCandleSense {
		val senseCandle: Boolean = heroWithCandles.hero.doInPassage {
			heroWithCandles.candles.hasCandleIn(it.direction)
		}
		return HeroWithCandleSense(heroWithCandles.hero, senseCandle)
	}
}
