package v.o.i.d.java9.varhandles;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicOperations {

	private final VarHandle intHandle;

	public int var;

	public AtomicOperations() throws NoSuchFieldException, IllegalAccessException {
		intHandle = MethodHandles.lookup().in(AtomicOperations.class).findVarHandle(AtomicOperations.class, "var", int.class);
		var = 0;
	}

	public void increment() {
		System.out.println(Thread.currentThread().getName() + " was number " + intHandle.getAndAdd(this, 1));
	}

	public static void main(String args[]) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
		AtomicOperations sample = new AtomicOperations();
		runConcurrently(sample::increment);
	}

	private static void runConcurrently(Runnable runnable) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100; i++) {
			executor.submit(runnable);
		}
		executor.shutdown();
	}
}
