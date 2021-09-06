package v.o.i.d.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutures {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		var cf = CompletableFuture
				.runAsync(() -> {
					System.out.println("1");
					throw new RuntimeException("xxxxxx");
				})
				.thenRun(() -> {
					System.out.println("2");
				})
				.exceptionally(th -> {
					System.out.println("ouch");
					th.printStackTrace();
					return null;
				});

		cf.get();
	}
}
