package v.o.i.d.worker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static v.o.i.d.worker.RandomWorkload.randomWork;
import static v.o.i.d.worker.RandomWorkload.randomWorkList;

public class WorkerExample2 {

	private static List<Integer> createQueueList() {
		return Collections.synchronizedList(new ArrayList<>());
	}

	private static final Executor executor = Executors.newSingleThreadExecutor();

	private static final AtomicInteger counterProcessed = new AtomicInteger(0);
	private static final AtomicInteger counterWork = new AtomicInteger(0);

	/**
	 * This method is called by the scheduler.
	 */
	public static void runOneIteration() {
		try {
			List<Integer> orgIds = randomWorkList();
			counterWork.addAndGet(orgIds.size());

			process(orgIds);
		} catch (Exception e) {
			System.out.println("Top level service failure");
		}
	}

	public static void queueForRebuild(int orgId) {
		process(Collections.singletonList(orgId));
		counterWork.incrementAndGet();
	}

	private static void process(List<Integer> orgIds) {
		if (orgIds.isEmpty()) {
			return;
		}
		executor.execute(() -> {
			for (Integer orgId : orgIds) {
				counterProcessed.incrementAndGet();
			}
		});
	}

	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		List<Future<?>> futures = new ArrayList<>();

		int i = 100000;
		while (i-- > 0) {
			futures.add(es.submit(WorkerExample::runOneIteration));
			futures.add(es.submit(() -> queueForRebuild(randomWork())));
			queueForRebuild(randomWork());
		}

		futures.forEach(f -> {
			try { f.get();} catch (Exception ignore) {}
		});
		System.out.println("Finished jobs: " + futures.size());

		System.out.println("---");
		System.out.println("Submitted: " + counterWork.get());
		System.out.println("Processed: " + counterProcessed.get());
	}

}
