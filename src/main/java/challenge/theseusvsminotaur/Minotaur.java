package challenge.theseusvsminotaur;

public class Minotaur extends Hero {

	public Minotaur(Passage passage, Cavern nextCavern) {
		super(passage, nextCavern);
	}

	public boolean senseCandleInNextCavern() {
		assertInPassage();
		return nextCavern.hasCandle();
	}

	public void enterLeftUnvisitedPassage() {
		assertInCavern();
		enterNextUnvisitedPassage(passage -> currentCavern.findPreviousPassageFrom(passage));
	}

	public boolean killTheseusIfInPassage(final Theseus theseus) {
		assertInPassage();
		return currentPassage == theseus.currentPassage;
	}
}
