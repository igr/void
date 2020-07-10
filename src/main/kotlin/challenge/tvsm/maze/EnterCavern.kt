package challenge.tvsm.maze

import challenge.tvsm.model.HeroK

object EnterCavern: (HeroK) -> Unit {
	override fun invoke(hero: HeroK) {
		hero.moveInPassage { it.stepIntoCavern() }
	}
}

