package challenge.tvsm

import challenge.tvsm.ctx.MazeK
import challenge.tvsm.maze.*

class MazeSolver(private val maze: MazeK) {

	fun solve() {
		var theend = false;

		while (true) {

			maze.minotaurAndCandles {
				SenseCandleInNextCavern(it)
					.ifCandleSeen(
						TurnBack
					)
				}

				.minotaur(EnterCavern)
				.theseus(EnterCavern)

				.thesesAndCandles {
					PutCandleInHeroesCavern
				}

				.heroes {
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

//			if (minotaur.senseCandleInNextCavern()) {
//				minotaur.turnBack();
//			}
//
//			minotaur.enterCavern();
//			theseus.enterCavern();
//
//			theseus.lightCandleAndPutInCavern();
//
//			if (theseus.killIfInCavern(minotaur)) {
//				printMinotaurKill();
//				break;
//			}
//
//			minotaur.enterLeftUnvisitedPassage();
//			theseus.enterRightUnvisitedPassage();
//
//			if (minotaur.killIfInPassage(theseus)) {
//				printTheseusKill();
//				break;
//			}
		}
	}

}