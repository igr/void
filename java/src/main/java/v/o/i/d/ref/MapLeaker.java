package v.o.i.d.ref;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Question #1
 * Why we get OutOfMemoryError when we remove the line P1?
 * Because internal queue of executors gets overloaded.
 *
 * Question #2
 * If we have weak reference on a thread, how do we know whn thread is finished?
 */
public class MapLeaker {

	private ExecutorService exec = Executors.newFixedThreadPool(5);

	private Map<Task, TaskStatus> taskStatus = Collections.synchronizedMap(new HashMap<>());
//	private Map<Task, TaskStatus> taskStatus = Collections.synchronizedMap(new WeakHashMap<>());
//	private Map<Task, TaskStatus> taskStatus = Collections.synchronizedMap(new ReferenceMap<>(ReferenceType.WEAK, ReferenceType.STRONG));
	private Random rnd = new Random();

	private enum TaskStatus {
		NOT_STARTED, STARTED, FINISHED
	}

	private class Task implements Runnable {
		int[] buffer = new int[rnd.nextInt(1_000_000)];
		@Override
		public void run() {
			taskStatus.put(this, TaskStatus.STARTED);
			sleep(rnd.nextInt(100));       // do something
			taskStatus.put(this, TaskStatus.FINISHED);
			System.out.println(taskStatus.size());
		}
	}

	private Task newTask() {
		Task t = new Task();
		taskStatus.put(t, TaskStatus.NOT_STARTED);
		exec.execute(t);
		return t;
	}

	private static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// ignore
		}
	}

	// ---------------------------------------------------------------- main

	public static void main(String[] args) {
		MapLeaker mapLeaker = new MapLeaker();
		for (int i = 0; i < 100000; i++) {
			sleep(50);          // P1
			mapLeaker.newTask();
		}
	}
}
