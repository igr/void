package challenge.theseusvsminotaur;

public class Theseus extends Hero {
	public Theseus(Passage passage, Cavern nextCavern) {
		super(passage, nextCavern);
	}

	public boolean killIfInCavern(final Minotaur minotaur) {
		assertInCavern();
		return currentCavern.equals(minotaur.currentCavern);
	}

	public void lightCandleAndPutInCavern() {
		assertInCavern();
		currentCavern.acceptCandle();
	}

	public void enterRightUnvisitedPassage() {
		assertInCavern();
		enterNextUnvisitedPassage(passage -> currentCavern.findNextPassageFrom(passage));
	}
}
