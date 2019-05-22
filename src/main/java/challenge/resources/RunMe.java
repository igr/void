package challenge.resources;

import challenge.resources.check.Checks;
import challenge.resources.domain.Dataset;
import challenge.resources.domain.Health;
import challenge.resources.domain.Resource;
import challenge.resources.domain.Score;
import jodd.json.JsonSerializer;

import java.util.Map;
import java.util.function.Function;
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
			.map(this::calcScore)
			.collect(Collectors.toMap(Score::getResourceId, Function.identity()));
	}

	private Score calcScore(final Resource resource) {
		Score score = new Score(resource.getId());

		Checks.createAllChecks().forEach(resourceCheck -> {
			Health health = resourceCheck.apply(resource);
			score.addHealth(resourceCheck.id(), health);
		});

		return score;
	}
}
