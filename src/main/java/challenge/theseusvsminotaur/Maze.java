package challenge.theseusvsminotaur;

import challenge.theseusvsminotaur.exception.InvalidMazeStructureException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public class Maze {

	private final Set<Cavern> allCaverns = new HashSet<>();
	private Theseus theseus = null;
	private Minotaur minotaur = null;

	/**
	 * Creates uni-directional connections (i.e. passages) between cavern and list of connected cavers.
	 * It is expected that all caverns will be connected using this method.
	 */
	public void connectCaverns(final String cavernName, final List<String> connectedCavernNames) {
		final Cavern thisCavern = resolveCavernByName(cavernName);

		connectedCavernNames.forEach(name -> {
			final Cavern otherCavern = resolveCavernByName(name);
			connectToCavernWithPassage(thisCavern, otherCavern);
		});
	}

	private void connectToCavernWithPassage(final Cavern thisCavern, final Cavern otherCavern) {
		thisCavern.findPassageTo(otherCavern)
			.ifPresentOrElse(passage -> {}, () -> {
				final Passage passage =
					otherCavern.findPassageTo(thisCavern)
						.orElseGet(() -> new Passage(thisCavern, otherCavern));

				thisCavern.addPassage(passage);
			});
	}

	private Cavern resolveCavernByName(final String cavernName) {
		for (Cavern cavern : allCaverns) {
			if (cavern.getName().equals(cavernName)) {
				return cavern;
			}
		}

		// cavern not found
		final Cavern newCavern = new Cavern(cavernName);
		allCaverns.add(newCavern);
		return newCavern;
	}

	// ---------------------------------------------------------------- heros

	public void putTheseusInPassageBetween(final String theseusCavernNameFrom, final String theseusCavernNameTo) {
		theseus = putHeroInPassage(theseusCavernNameFrom, theseusCavernNameTo, Theseus::new);
	}

	public void putMinotaurInPassageBetween(final String minotaurCavernNameFrom, final String minotaurCavernNameTo) {
		minotaur = putHeroInPassage(minotaurCavernNameFrom, minotaurCavernNameTo, Minotaur::new);
	}

	private <T extends Hero> T putHeroInPassage(
			final String cavernNameFrom, final String cavernNameTo, BiFunction<Passage, Cavern, T> heroFactory) {

		final Cavern cavernFrom = resolveCavernByName(cavernNameFrom);
		final Cavern cavernTo = resolveCavernByName(cavernNameTo);
		final Passage passage = cavernFrom
			.findPassageTo(cavernTo)
			.orElseThrow(() -> new InvalidMazeStructureException("Caverns are not connected"));

		return heroFactory.apply(passage, cavernTo);
	}

	// ---------------------------------------------------------------- solver

	/**
	 * Solves the puzzle.
	 */
	public void solvePuzzle() {
		while(true) {
			if (minotaur.senseCandleInNextCavern()) {
				minotaur.turnBack();
			}

			minotaur.enterCavern();
			theseus.enterCavern();

			theseus.lightCandleAndPutInCavern();
			if (theseus.killMinotaurIfInCavern(minotaur)) {
				printMinotaurKill();
				break;
			}

			minotaur.enterLeftUnvisitedPassage();
			theseus.enterRightUnvisitedPassage();

			if (minotaur.killTheseusIfInPassage(theseus)) {
				printTheseusKill();
				break;
			}
		}
	}

	private void printTheseusKill() {
		minotaur.getCurrentPassage().ifPresent(p -> System.out.println("Theseus is killed in " + p));
	}

	private void printMinotaurKill() {
		theseus.getCurrentCavern().ifPresent(c -> System.out.println("The Minotaur is slain in " + c));
	}
}
