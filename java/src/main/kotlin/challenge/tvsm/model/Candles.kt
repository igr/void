package challenge.tvsm.model

class Candles(set: Set<CavernK> = setOf()) {
	private val candles: Set<CavernK> = set

	fun putCandleInCavern(position: PositionInCavernK): Candles {
		return Candles(candles + position.cavern)
	}

	fun hasCandleIn(cavern: CavernK): Boolean {
		return candles.contains(cavern)
	}
}