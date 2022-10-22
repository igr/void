package v.o.i.d.reactor;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class Runner {

	private static final Consumer<Object> println = System.out::println;

	public static void main(String[] args) {
		Fluxer f = new Fluxer().go();

		// *** concat & concatWith

		// By the time source1 completes, source2 had already emitted some data which we lost (It is a hot publisher).
		// So we see only the latest from source2 – starting from element 2. We do not see element 0 and 1 from source2
		// as concat method was busy collecting elements from source1 as it has not completed then.

//		Flux.concat(f.flux1, f.flux2).subscribe(println);
//		f.flux1.concatWith(f.flux2).subscribe(println);

		// *** combine

//		Flux.combineLatest(f.flux1, f.flux2, (a, b) -> a + " :: " + b).subscribe(println);

		// *** merge

		// concat method would start connecting to source2 only after source1 is completed.
		// But merge connects to all the sources immediately.

//		Flux.merge(f.flux1, f.flux2).subscribe(println);

		// *** zip

		// The zip method collects the data from sources and places them inside a Tuple
		// ONLY MATCHED!

		Flux.zip(f.flux1, f.flux2).subscribe(println);

	}
}
