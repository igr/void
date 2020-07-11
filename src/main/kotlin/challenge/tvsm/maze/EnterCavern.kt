package challenge.tvsm.maze

import challenge.tvsm.model.HeroK

val enterCavern = fun(hero: HeroK) {
	hero.moveInPassage { it.stepIntoCavern() }
}

