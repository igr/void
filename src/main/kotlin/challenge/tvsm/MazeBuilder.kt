package challenge.tvsm

import challenge.tvsm.ctx.MazeK
import challenge.tvsm.model.*

fun createMazeK(cavernPairs: Iterable<Pair<String, String>>,
                theseusPair: Pair<String, String>,
                minotaurPair: Pair<String, String>): MazeK {

	// collect caverns first (as passages uses caverns)
	val caverns: Set<MutableCavernK> = cavernPairs
		.map { it.first }
		.distinct()
		.map { MutableCavernK(it) }
		.toSet()

	fun cavernOf(name: String): MutableCavernK {
		return caverns.find { it.name == name } ?: throw IllegalArgumentException("Non-existing cavern: $name")
	}

	// then create _distinct_ passages
	val passages = cavernPairs
		.map { MutablePassageK(cavernOf(it.first), cavernOf(it.second)) }
		.toSet()

	// then add passages to caverns
	fun passageOf(from: String, to: String) : MutablePassageK {
		return passages.find {
			(it.a.name == from && it.b.name == to) ||
				(it.a.name == to && it.b.name == from)
		} ?: throw IllegalArgumentException("Invalid passage $from - $to")
	}

	fun passageOf(pair: Pair<String, String>) : MutablePassageK {
		return passageOf(pair.first, pair.second)
	}

	cavernPairs
		.map { Pair(cavernOf(it.first), passageOf(it)) }
		.forEach { it.first.addPassage(it.second) }


	// finally put heroes in the maze

	val theseus = HeroTheseusK(PositionInPassageK(passageOf(theseusPair), cavernOf(theseusPair.second), null))
	val minotaur = HeroMinotaurK(PositionInPassageK(passageOf(minotaurPair), cavernOf(minotaurPair.second), null))

	// *** We don't need to store the passages and caverns!
	return MazeK(theseus, minotaur)
}
