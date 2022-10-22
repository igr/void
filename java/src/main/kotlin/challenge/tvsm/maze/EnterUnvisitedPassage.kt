package challenge.tvsm.maze

import challenge.tvsm.model.HeroK

val enterLeftUnmarkedExit = fun(hero: HeroK) {
	hero.doInCavern {
		var pp = it.previousPassage
		while (true) {
			val leftPassage = it.cavern.leftPassageOf(pp)
			val otherCave = leftPassage.otherEndOf(it.cavern)
			if (!hero.usedExitTo(otherCave)) {
				hero.move(it.enterPassage(leftPassage))
				break
			}
			pp = leftPassage
		}
	}
}

val enterRightUnmarkedExit = fun(hero: HeroK) {
	hero.doInCavern {
		var pp = it.previousPassage
		while (true) {
			val rightPassage = it.cavern.rightPassageOf(pp)
			val otherCave = rightPassage.otherEndOf(it.cavern)
			if (!hero.usedExitTo(otherCave)) {
				hero.move(it.enterPassage(rightPassage))
				break
			}
			pp = rightPassage
		}
	}

}