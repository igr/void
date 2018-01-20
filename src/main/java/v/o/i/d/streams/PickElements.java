package v.o.i.d.streams;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PickElements {

	public static void main(String[] args) {
		final List<String> friends =
		    Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

		List<String> startsWithN =
				friends.stream()
				.filter(name -> name.startsWith("N"))
				.collect(toList());

		System.out.println(startsWithN);
	}
}