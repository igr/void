package challenge.tvsm.maze

import challenge.tvsm.model.HeroK

val turnBack = fun(hero: HeroK) {
	return hero.moveInPassage { it.changeDirection() }
}