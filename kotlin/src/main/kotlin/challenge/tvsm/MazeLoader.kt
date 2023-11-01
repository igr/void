package challenge.tvsm

import jodd.util.ResourcesUtil
import jodd.util.StringPool

// *** just a quick-and-dirty text parser
class MazeLoader {
	// utility class that controls the lifespan and usage of caverns set
	class Caverns {
		var caverns = new()

		private fun new(): MutableList<Pair<String, String>> {
			return mutableListOf()
		}

		fun add(pairs: Iterable<Pair<String, String>>) {
			caverns.addAll(pairs)
		}

		fun get(): Iterable<Pair<String, String>> {
			val result = caverns;
			caverns = new()
			return result;
		}
	}

	fun loadMazes(): List<MazeK> {
		val mazes = mutableListOf<MazeK>()
		val caverns = Caverns()
		val input = ResourcesUtil.getResourceAsString("challenge/theseusvsminotaur/in1.txt")

		input
			.lines()
			.forEach { line ->
				println(line)

				if (isEndOfFile(line)) {
					return@forEach
				}

				if (isEndOfMaze(line)) {
					// positions of Theseus and Minotaur

					val theseus = line[1].toString() to line[2].toString()
					val minotaur = line[3].toString() to line[4].toString()

					mazes.add(createMazeK(caverns.get(), theseus, minotaur))
				} else {
					// caverns

					val ndx = line.indexOf(':')
					val cavernName = line.substring(0, ndx)

					line.substring(ndx + 1)
						.toCharArray()
						.map { it.toString() }
						.map { cavernName to it }
						.toList()
						.let {
							caverns.add(it)
						}
				}
			}

		return mazes
	}

	private fun isEndOfMaze(line: String): Boolean {
		return line.startsWith(StringPool.AT)
	}

	private fun isEndOfFile(line: String): Boolean {
		return line == StringPool.HASH
	}

}