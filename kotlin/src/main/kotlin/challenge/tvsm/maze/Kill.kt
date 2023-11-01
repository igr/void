package challenge.tvsm.maze

import challenge.tvsm.TwoHeroes

object TheseusKillMinotaur: (TwoHeroes) -> Unit {
	override fun invoke(twoHeroes: TwoHeroes) {
		println("${twoHeroes.theseus.name} kill ${twoHeroes.minotaur.name}")
	}
}

object MinotaurKillTheseus: (TwoHeroes) -> Unit {
	override fun invoke(twoHeroes: TwoHeroes) {
		println("${twoHeroes.minotaur.name} kill ${twoHeroes.theseus.name}")
	}
}