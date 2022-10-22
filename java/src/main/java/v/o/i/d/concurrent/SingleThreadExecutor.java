package v.o.i.d.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutor {

	static Runnable runnable = () -> System.out.println(Thread.currentThread().getName());

	public static void main(String[] args) {
		ScheduledExecutorService ex1 = Executors.newSingleThreadScheduledExecutor();
		ScheduledExecutorService ex2 = Executors.newSingleThreadScheduledExecutor();

		ex1.execute(runnable);
		ex2.execute(runnable);

		ex1.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
		ex2.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}
}
