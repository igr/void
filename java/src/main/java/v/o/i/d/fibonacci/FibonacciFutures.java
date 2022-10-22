package v.o.i.d.fibonacci;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FibonacciFutures {

	public static Future<Integer> fib(Integer n) {
		if (n.intValue() < 2) {
			return future(() -> n);
		}

		Future<Integer> t1 = future(() -> fib(n - 1).get());
		Future<Integer> t2 = future(() -> fib(n - 2).get());

		return future(() -> t1.get() + t2.get());
	}


	public static <V> Future<V> future(Callable<V> callable) {
		return pool.submit(callable);
	}

	static ExecutorService pool = Executors.newCachedThreadPool();

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		System.out.println(fib(10).get());
		pool.shutdown();
	}

}