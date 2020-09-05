package v.o.i.d.generics;

public class Pair<K, V> {
	final K first;
	final V second;
	private V[] arr;

	public Pair(K first, V second) {
		this.first = first;
		this.second = second;

		// You cannot create objects or arrays of a parameterized type
		//arr = new V[3];
		arr = (V[]) new Object[3];
	}

	public K first() {
		return first;
	}

	public V second() {
		return second;
	}

	public static <A, B> Pair<B, A> swap(Pair<A, B> p) {
		return new Pair<>(p.second(), p.first());
	}

}
