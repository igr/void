package v.o.i.d.generics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Farm {

	private List<Animal> animalList = new ArrayList<>();
	// allows anything that is a subtype of Foo in the same list.
	// e.g. List<Animal> could store both Giraffes and Zebras

	private Set<? extends Animal> animalListAlt = new HashSet<>();
	// binds to a particular Foo subtype and allows ONLY that.
	// e.g. List<? extends Animal> might store only Giraffes but not Zebras


	// upper bound; accepts the given supertype or any of its subtypes.
	public <A extends Animal> void addAnimal(A animal) {
		animalList.add(animal);
	}

	public void addAll(Set<? extends Animal> newSet) {      // mora extends
		///
	}

}
