package challenge.resources.domain;

import java.util.ArrayList;
import java.util.List;

public class Dataset {
	private final List<Resource> resources = new ArrayList<>();

	public List<Resource> getResources() {
		return resources;
	}
}
