package challenge.resources;

import challenge.resources.domain.Dataset;
import challenge.resources.domain.Resource;

import java.util.List;

public class Datasets {

	public static Dataset makeExample1() {
		final Dataset dataset = new Dataset();
		List<Resource> resources = dataset.getResources();

		resources.add(new Resource("R1", "url1"));
		resources.add(new Resource("R2", "url2"));
		resources.add(new Resource("R3", "url3"));

		return dataset;
	}
}
