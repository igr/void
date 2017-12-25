package v.o.i.d.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {

	static Runnable runnable = () -> System.out.println(Thread.currentThread().getName());

	public static void main(String[] args) {
		Executor ex1 = Executors.newSingleThreadScheduledExecutor();
		Executor ex2 = Executors.newSingleThreadScheduledExecutor();

		ex1.execute(runnable);
		ex2.execute(runnable);
	}
}
