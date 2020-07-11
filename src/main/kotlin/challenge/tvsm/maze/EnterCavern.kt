package challenge.tvsm.maze

import challenge.tvsm.model.HeroK

val enterCavern = fun(hero: HeroK) {
	return hero.doInPassage {
		it.stepIntoCavern()
	}.let {
		hero.move(it)
	}
}

