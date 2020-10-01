package v.o.i.d.parallelAdder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class RunMe {

	public static void main(String[] args) {
		// naive

		NaiveCounter naiveCounter = new NaiveCounter();
		long elapsed = count(naiveCounter::increment);
		System.out.println("Naive: " + naiveCounter.count() + " in " + elapsed + "ms.");       // example: 9879737

		// naive2

		naiveCounter = new NaiveCounter();
		elapsed = count(naiveCounter::incrementSynchronized);
		System.out.println("NaiveSync: " + naiveCounter.count() + " in " + elapsed + "ms.");

		// AtomicLong

		AtomicLong atomicLong = new AtomicLong(0);
		elapsed = count(atomicLong::getAndIncrement);
		System.out.println("Atomic: " + atomicLong.get() + " in " + elapsed + "ms.");

		// LongAdder

		LongAdder longAdder = new LongAdder();
		elapsed = count(longAdder::increment);
		System.out.println("Long: " + longAdder.intValue() + " in " + elapsed + "ms.");

		// VarHandle

		VarHandleCounter varHandle = new VarHandleCounter();
		elapsed = count(varHandle::increment);
		System.out.println("Var: " + varHandle.count() + " in " + elapsed + "ms.");

	}

	private static long count(Runnable runnable) {
		ExecutorService executor = Executors.newCachedThreadPool();
		long now = System.currentTimeMillis();

		for (long i = 0; i < (long) 10_000_000; i++) {
			executor.execute(runnable);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return System.currentTimeMillis() - now;
	}

}
