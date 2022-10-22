package v.o.i.d.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.concurrent.ThreadLocalRandom;

import static jodd.util.ThreadUtil.sleep;

public class Fluxer {
	public final Sinks.Many<Object> source1;
	public final Sinks.Many<Object> source2;
	public final Flux<Object> flux1;
	public final Flux<Object> flux2;

	public Fluxer() {
		source1 = Sinks.many().multicast().directBestEffort();
		source2 = Sinks.many().multicast().directBestEffort();

		flux1 = source1.asFlux();
		flux2 = source2.asFlux();
	}

	public Fluxer go() {
//		flux1.subscribe(x -> System.out.println("1> " + x));
//		flux2.subscribe(x -> System.out.println("2> " + x));

		Runnable r1 = () -> {
			for (int i = 0; i < 5; i++) {
				int idle = ThreadLocalRandom.current().nextInt(100, 1000);
				sleep(idle);
				source1.tryEmitNext("source1 - " + i);
			}
			source1.tryEmitComplete();
		};

		Runnable r2 = () -> {
			for (int i = 0; i < 10; i++) {
				int idle = ThreadLocalRandom.current().nextInt(100, 1000);
				sleep(idle);
				source2.tryEmitNext("source2 - " + i);
			}
			source2.tryEmitComplete();
		};

		new Thread(r1).start();
		new Thread(r2).start();

		return this;
	}
}
