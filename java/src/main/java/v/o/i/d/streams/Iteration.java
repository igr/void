package v.o.i.d.streams;

import java.util.Arrays;
import java.util.List;

public class Iteration {
	public static void main(String[] args) {
		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

		friends.forEach(System.out::println);
	}
}