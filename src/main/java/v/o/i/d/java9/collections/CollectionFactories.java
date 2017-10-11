package v.o.i.d.java9.collections;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactories {

	public static void main(String args[]) {
		List<Integer> listOfNumbers = List.of(1, 2, 3);
		System.out.println(listOfNumbers);

		Set<Integer> setOfNumbers = Set.of(1, 2, 3);
		System.out.println(setOfNumbers);

		Map<String, String> mapOfString = Map.of("Key1", "Val1", "Key2", "Val2");
		System.out.println(mapOfString);
	}
}
