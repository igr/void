package challenge.tvsm.maze

import challenge.tvsm.model.HeroK

val turnBack = fun(hero: HeroK): HeroK {
	return hero.doInPassage {
		it.changeDirection()
	}.let {
		hero.move(it)
	}
}