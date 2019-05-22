package challenge.resources;

import challenge.resources.check.CheckHealth;
import challenge.resources.check.Checks;
import challenge.resources.domain.Dataset;
import challenge.resources.domain.Health;
import challenge.resources.domain.Resource;
import challenge.resources.domain.Score;
import jodd.json.JsonSerializer;
import v.o.i.d.function.IdentityMonad;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class RunMe {

	public static void main(String[] args) {
		final Dataset ex1 = Datasets.makeExample1();

		Map<String, Score> map = new RunMe().run(ex1);
		System.out.println(JsonSerializer.createPrettyOne().deep(true).serialize(map));
	}

	private Map<String, Score> run(final Dataset dataset) {
		return dataset
			.getResources()
			.stream()

			.map(Checks::createAllChecks)
			.flatMap(Collection::stream)

			.map(Supplier::get)

			.collect(Collectors.groupingBy(
				CheckHealth::getResourceId,
				(Supplier<Map<String, Score>>) HashMap::new,
				new Collector<CheckHealth, Score, Score>() {
					@Override
					public Supplier<Score> supplier() {
						return Score::new;
					}

					@Override
					public BiConsumer<Score, CheckHealth> accumulator() {
						return (score, checkHealth) -> score.addHealth(checkHealth.getCheckId(), checkHealth.getHealth());
					}

					@Override
					public BinaryOperator<Score> combiner() {
						return (score, score2) -> score;
					}

					@Override
					public Function<Score, Score> finisher() {
						return score -> score;
					}

					@Override
					public Set<Characteristics> characteristics() {
						return Collections.emptySet();
					}
				}
			));
	}

	private Score calcScore2(final Resource resource) {
		return
			IdentityMonad
				.of(resource)
				.map(Checks::createAllChecks)

				.bind(list -> IdentityMonad.of(list.stream()))
				.get()

				.map(Supplier::get)
				.reduce(new Score(),
					(score, checkHealth) -> score.addHealth(checkHealth.getCheckId(), checkHealth.getHealth()),
					(score1, score2) -> score2);
	}

	private Score calcScore1(final Resource resource) {
		final Score score = new Score();

		Checks.createAllChecks().forEach(resourceCheck -> {
			Health health = resourceCheck.apply(resource);
			//score.addHealth(resourceCheck.id(), health);
			score.addHealth(resourceCheck.getClass().getSimpleName(), health);
		});

		return score;
	}
}
