package v.o.i.d.fibonacci;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FibonacciPromisse {

	public static CompletableFuture<Integer> fib(Integer n) {
		if (n.intValue() < 2) {
			return CompletableFuture.supplyAsync(() -> n, pool);
		}

		CompletableFuture<Integer> t1 = fib(n-1);
		CompletableFuture<Integer> t2 = fib(n-2);

		return t1.thenCombineAsync(t2, (a, b) -> a+b, pool);
	}

	static ExecutorService pool = Executors.newCachedThreadPool();

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		System.out.println(fib(10).get());
		pool.shutdown();
	}
}