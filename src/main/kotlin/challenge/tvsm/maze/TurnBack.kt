package challenge.tvsm.maze

import challenge.tvsm.model.HeroK

object TurnBack: (HeroK) -> Unit {
	override fun invoke(hero: HeroK) {
		return hero.moveInPassage { it.changeDirection() }
	}
}
