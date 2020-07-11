package challenge.tvsm.maze

import challenge.tvsm.model.HeroK

object EnterLeftUnmarkedExit: (HeroK) -> Unit {
	override fun invoke(hero: HeroK) {
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
}

object EnterRightUnmarkedExit: (HeroK) -> Unit {
	override fun invoke(hero: HeroK) {
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

}