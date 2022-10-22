package v.o.i.d.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PickALongest {

	public static void main(String[] args) {
		final List<String> friends =
		    Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

		final Optional<String> aLongName =
			friends
				.stream()
				.reduce((name1, name2) -> name1.length() >= name2.length() ? name1 : name2);

		System.out.println(aLongName);

	}
}