package challenge.tvsm.model

object Candles {
	private val candles: Set<CavernK> = mutableSetOf()

	fun putCandleInCavern(position: PositionInCavernK) {
		candles + position.cavern
	}

	fun hasCandleIn(cavern: CavernK): Boolean {
		return candles.contains(cavern)
	}
}