package v.o.i.d.ref;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapLeaker {

	// ThreadPoolExecutor
	public ExecutorService exec = Executors.newFixedThreadPool(5);
	//public Map<Task, TaskStatus> taskStatus = Collections.synchronizedMap(new HashMap<Task, TaskStatus>());
	public Map<Task, TaskStatus> taskStatus = Collections.synchronizedMap(new WeakHashMap<Task, TaskStatus>());
//	public Map<Task, TaskStatus> taskStatus = Collections.synchronizedMap(new ReferenceMap<Task, TaskStatus>(ReferenceType.WEAK, ReferenceType.STRONG));
	private Random rnd = new Random();

	private enum TaskStatus {
		NOT_STARTED, STARTED, FINISHED
	}

	private class Task implements Runnable {
		int[] temp = new int[rnd.nextInt(1000)];
		@Override
		public void run() {
			taskStatus.put(this, TaskStatus.STARTED);
			sleep(rnd.nextInt(100));       // do something
			taskStatus.put(this, TaskStatus.FINISHED);
			System.out.println(taskStatus.size());      // show size!
		}

	}

	public Task newTask() {
		Task t = new Task();
		taskStatus.put(t, TaskStatus.NOT_STARTED);
		exec.execute(t);
		return t;
	}

	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// ignore
		}
	}

	// ---------------------------------------------------------------- main

	public static void main(String[] args) {
//		sleep(10000);
		System.out.println("---start---");
		MapLeaker ml = new MapLeaker();
		for (int i = 0; i < 100000; i++) {
			sleep(50);          // P1
			ml.newTask();
		}
	}
}











/*
 Pitanje #1:
 Zašto imamo OutOfMemoryError kada uklonimo red P1?
 Zato sto interni queue of executora se prepuni.
*/

/*
Pitanje #2:
Čekaj malo, ali kada imamo weak reference na thread, kako znamo kada je thread gotov?
*/