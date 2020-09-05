package v.o.i.d.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// parametric polymorphism: Ability for a function or type to be written in such a way that
// it handles values identically without depending on knowledge of their types.
// in ML language in 1976
public class GenericsExample {

	public static void main(String[] args) {

		/* ---*****--- */

		List<String> names = new ArrayList<>();
		names.add("Rick");
		names.add("Marty");

		//names.add(12);

		/* ---*****--- */

		Pair<String, Integer> pair = new Pair<>("left", 173);

		/* ---*****--- */

		Farm farm = new Farm();
		farm.addAnimal(new Zebra());

		/* ---*****--- */

		Set<Giraffe> gs = Set.of(new Giraffe());
		farm.addAll(gs); // NOT WORKING

		/* ---*****--- */


		List<Giraffe> l1 = new ArrayList<>(List.of(new Giraffe()));
		List<Zebra> l2 = new ArrayList<>(List.of(new Zebra()));
		List<Animal> l3 = new ArrayList<>(List.of(new Zebra()));
		List<? extends Animal> l4 = new ArrayList<>(List.of(new Zebra()));

//		copy(l2, l1);
//		copy(l2, l3);

		System.out.println("-----------------");
		copy(l3, l1).forEach(System.out::println);

		//copy(l1, l3).forEach(System.out::println);

	}

	public static <T> List<? super T> copy(List<? super T> dest, List<? extends T> src) {
		dest.addAll(src);
		return dest;
	}

}
