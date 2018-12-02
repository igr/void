package challenge.theseusvsminotaur;

import challenge.theseusvsminotaur.exception.IllegalActionException;
import challenge.theseusvsminotaur.exception.InvalidMazeStructureException;

import java.util.HashMap;
import java.util.Map;

public class Passage {

	private final Map<Hero, boolean[]> heroMarks = new HashMap<>();
	private final Cavern cavernOne;
	private final Cavern cavernTwo;

	public Passage(final Cavern cavernOne, final Cavern cavernTwo) {
		this.cavernOne = cavernOne;
		this.cavernTwo = cavernTwo;
	}

	public boolean isBetween(final Cavern cavernA, final Cavern cavernB) {
		return
			cavernA.equals(cavernOne) && cavernB.equals(cavernTwo) ||
			cavernA.equals(cavernTwo) && cavernB.equals(cavernOne);
	}

	public Cavern getOppositeCavernOf(final Cavern cavern) {
		if (cavernOne.equals(cavern)) {
			return cavernTwo;
		}
		if (cavernTwo.equals(cavern)) {
			return cavernOne;
		}
		throw new InvalidMazeStructureException("Not connected to this passage: " + cavern);
	}

	public boolean isMarkedExitFrom(final Cavern cavern, final Hero hero) {
		final boolean[] marks = resolveHeroMarks(hero);

		if (cavern.equals(cavernOne)) {
			return marks[0];
		}
		if (cavern.equals(cavernTwo)) {
			return marks[1];
		}
		throw new InvalidMazeStructureException("Cavern not connected to a passage");
	}

	public void markExitFrom(final Cavern cavern, final Hero hero) {
		final boolean[] marks = resolveHeroMarks(hero);

		if (cavern.equals(cavernOne)) {
			if (marks[0]) {
				throw new IllegalActionException("Already marked");
			}
			marks[0] = true;
			return;
		}
		if (cavern.equals(cavernTwo)) {
			if (marks[1]) {
				throw new IllegalActionException("Already marked");
			}
			marks[1] = true;
			return;
		}
		throw new InvalidMazeStructureException("Cavern not connected to a passage");
	}

	private boolean[] resolveHeroMarks(final Hero hero) {
		return heroMarks.computeIfAbsent(hero, h -> new boolean[2]);
	}

	@Override
	public String toString() {
		return "Passage{" + cavernOne.getName() + ',' + cavernTwo.getName() + '}';
	}

	protected void assertConnectedTo(final Cavern cavern) {
		if (cavernOne.equals(cavern)) {
			return;
		}
		if (cavernTwo.equals(cavern)) {
			return;
		}
		throw new InvalidMazeStructureException("Passage not connected to " + cavern);
	}
}
