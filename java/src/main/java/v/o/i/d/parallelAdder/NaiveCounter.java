package v.o.i.d.parallelAdder;

public class NaiveCounter {
	private long counter = 0;

	public void increment() {
		counter++;
	}

	public synchronized void incrementSynchronized() {
		counter++;
	}

	public long count() {
		return counter;
	}

}
