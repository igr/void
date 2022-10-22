package challenge.resources.check;

import challenge.resources.domain.Health;
import challenge.resources.domain.Resource;

import java.util.function.Supplier;

public abstract class ResourceCheck implements Supplier<CheckHealth> {
	// should be final, but for the case #1 is not
	protected Resource resource;

	public ResourceCheck(Resource resource) {
		this.resource = resource;
	}

	// exist just for case #1, to simulate the function.
	public Health apply(Resource resource) {
		this.resource = resource;
		return get().getHealth();
	}
}
