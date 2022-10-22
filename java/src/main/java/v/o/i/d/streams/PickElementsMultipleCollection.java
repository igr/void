package v.o.i.d.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class PickElementsMultipleCollection {
	/**
	 * Method that returns lambda.
	 */
	public static Predicate<String> checkIfStartsWith(final String letter) {
		return name -> name.startsWith(letter);
	}

	public static void main(String[] args) {
		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
		final List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");
		final List<String> editors = Arrays.asList("Brian", "Jackie", "John", "Mike");

		{
			final Predicate<String> startsWithN = name -> name.startsWith("N");

			long countFriends = friends.stream().filter(startsWithN).count();
			long countComrades = comrades.stream().filter(checkIfStartsWith("K")).count();

			System.out.println(countFriends);
			System.out.println(countComrades);

		}
		{
			final Function<String, Predicate<String>> startsWithLetter =
					letter -> name -> name.startsWith(letter);

			long editorsFriends = editors.stream().filter(startsWithLetter.apply("J")).count();

			System.out.println(editorsFriends);
		}
	}
}