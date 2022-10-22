package challenge.theseusvsminotaur;

import challenge.theseusvsminotaur.exception.IllegalActionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cavern {

	private final String name;
	private final List<Passage> passages = new ArrayList<>();
	private boolean hasCandle;

	public Cavern(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addPassage(Passage passage) {
		passage.assertConnectedTo(this);
		this.passages.add(passage);
	}

	public Optional<Passage> findPassageTo(final Cavern cavernTo) {
		for (Passage passage : passages) {
			if (passage.isBetween(this, cavernTo)) {
				return Optional.of(passage);
			}
		}
		return Optional.empty();
	}

	public void acceptCandle() {
		hasCandle = true;
	}

	public boolean hasCandle() {
		return hasCandle;
	}

	public Passage findNextPassageFrom(final Passage passage) {
		for (int i = 0; i < passages.size(); i++) {
			final Passage p = passages.get(i);

			if (p.equals(passage)) {
				i++;
				if (i == passages.size()) {
					i = 0;
				}
				return passages.get(i);
			}
		}
		throw new IllegalActionException("Can't find next passage in " + this);
	}

	public Passage findPreviousPassageFrom(final Passage passage) {
		for (int i = 0; i < passages.size(); i++) {
			final Passage p = passages.get(i);

			if (p.equals(passage)) {
				i--;
				if (i < 0) {
					i = passages.size() - 1;
				}
				return passages.get(i);
			}
		}
		throw new IllegalActionException("Can't find previous passage in " + this);
	}

	@Override
	public String toString() {
		return "Cavern{" + name + '}';
	}
}
