package challenge.tvsm.maze

import challenge.tvsm.ctx.HeroAndCandles
import challenge.tvsm.model.Candles

val putCandleInHeroesCavern = fun (ctx: HeroAndCandles): Candles {
	return ctx.hero.doInCavern {
		ctx.candles.putCandleInCavern(it)
	}
}