package challenge.tvsm.maze

import challenge.tvsm.model.Candles
import challenge.tvsm.model.HeroK

object PutCandleInHeroesCavern : (HeroK, Candles) -> Unit {
	override fun invoke(hero: HeroK, candles: Candles) {
		hero.doInCavern {
			candles.putCandleInCavern(it)
		}
	}
}