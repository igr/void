package challenge.theseusvsminotaur;

import jodd.io.StreamUtil;
import jodd.util.StringPool;
import jodd.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TM {

	public static void main(String[] args) throws IOException {
		new TM().loadMazesAndSolve();
	}

	private void loadMazesAndSolve() throws IOException {
		InputStream in = this.getClass().getResourceAsStream("in1.txt");
		String input = new String(StreamUtil.readChars(in));
		String[] lines = StringUtil.split(input, StringPool.NEWLINE);

		Maze maze = new Maze();

		for (String line : lines) {
			System.out.println(line);

			if (isEndOfMazeDefinition(line)) {
				// positions of Theseus and Minotaiur

				final String theseusCavernNameFrom = String.valueOf(line.charAt(1));
				final String theseusCavernNameTo = String.valueOf(line.charAt(2));

				maze.putTheseusInPassageBetween(theseusCavernNameFrom, theseusCavernNameTo);

				final String minotaurCavernNameFrom = String.valueOf(line.charAt(3));
				final String minotaurCavernNameTo = String.valueOf(line.charAt(4));

				maze.putMinotaurInPassageBetween(minotaurCavernNameFrom, minotaurCavernNameTo);

				maze.solvePuzzle();
				maze = new Maze();
				continue;
			}

			if (isEndOfFile(line)) {
				break;
			}

			int ndx = line.indexOf(':');

			final String cavernName = line.substring(0, ndx);

			List<String> connectedCavernNames = new ArrayList<>();

			line.substring(ndx + 1)
				.chars()
				.mapToObj(i -> String.valueOf((char)i))
				.forEach(connectedCavernNames::add);

			maze.connectCaverns(cavernName, connectedCavernNames);
		}
	}

	private boolean isEndOfMazeDefinition(String line) {
		return line.startsWith(StringPool.AT);
	}

	private boolean isEndOfFile(String line) {
		return line.equals(StringPool.HASH);
	}
}
