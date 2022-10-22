package challenge.theseusvsminotaur;

import challenge.theseusvsminotaur.exception.IllegalMoveException;

import java.util.Optional;
import java.util.function.Function;

public abstract class Hero {

	private final String name;
	private Passage previousPassage;
	protected Cavern currentCavern;
	protected Passage currentPassage;
	protected Cavern nextCavern;

	public Hero(final Passage passage, final Cavern nextCavern) {
		this.name = this.getClass().getSimpleName();
		this.currentPassage = passage;
		this.nextCavern = nextCavern;
	}

	public void enterCavern() {
		assertInPassage();
		currentCavern = nextCavern;
		previousPassage = currentPassage;
		currentPassage = null;
		nextCavern = null;
	}

	public void enterPassage(final Passage passage) {
		assertInCavern();
		previousPassage = null;
		currentPassage = passage;
		nextCavern = passage.getOppositeCavernOf(currentCavern);
		currentCavern = null;
	}

	public void turnBack() {
		assertInPassage();
		nextCavern = currentPassage.getOppositeCavernOf(nextCavern);
	}

	protected void assertInPassage() {
		if (currentPassage == null) {
			throw new IllegalMoveException("Not in passage");
		}
	}

	protected void assertInCavern() {
		if (currentCavern == null) {
			throw new IllegalMoveException("Not in the cavern");
		}
	}

	protected void enterNextUnvisitedPassage(final Function<Passage, Passage> nextPassageFinder) {
		Passage from = previousPassage;

		while (true) {
			Passage nextPassage = nextPassageFinder.apply(from);
			if (nextPassage == previousPassage) {
				// we made a loop
				break;
			}
			if (!nextPassage.isMarkedExitFrom(currentCavern, this)) {
				nextPassage.markExitFrom(currentCavern, this);
				enterPassage(nextPassage);
				return;
			}
			from = nextPassage;
		}
		throw new IllegalMoveException("Can't find unmarked passage to move on");
	}

	public Optional<Cavern> getCurrentCavern() {
		return Optional.ofNullable(currentCavern);
	}

	public Optional<Passage> getCurrentPassage() {
		return Optional.ofNullable(currentPassage);
	}

	@Override
	public String toString() {
		String s = name + " in ";
		if (currentPassage != null) {
			s += currentPassage + " to " + nextCavern.getName();
		}
		if (currentCavern != null) {
			s += currentCavern;
		}
		return s;
	}
}
