package challenge.tvsm

import challenge.tvsm.ctx.HeroAndCandles
import challenge.tvsm.maze.*

class MazeSolver(private val maze: MazeK) {

	fun solve() {
		var theend = false;

		while (true) {

			with(maze) {
				HeroAndCandles(minotaur, candles)
			}.let {
				senseCandleInNextCavern(it)
			}
			.ifCandleSeen {
				turnBack(it)
			}

			maze.minotaur(enterCavern)
				.theseus(enterCavern)

			with(maze) {
				HeroAndCandles(theseus, candles)
			}.let {
				putCandleInHeroesCavern(it)
			}

			maze.heroes {
				InSamePlace(it) {
					TheseusKillMinotaur(it)
					theend = true
				}
			}

			if (theend) break

			maze
				.minotaur(EnterLeftUnmarkedExit)
				.theseus(EnterRightUnmarkedExit)

				.heroes {
					InSamePlace(it) {
						MinotaurKillTheseus(it)
						theend = true
					}
				}

			if (theend) break
		}
	}

}