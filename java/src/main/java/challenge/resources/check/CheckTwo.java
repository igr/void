package challenge.resources.check;

import challenge.resources.domain.Health;
import challenge.resources.domain.Resource;

public class CheckTwo extends ResourceCheck {
	public CheckTwo(Resource resource) {
		super(resource);
	}

	@Override
	public CheckHealth get() {
		return new CheckHealth(resource.getId(), "c2", Health.MEDIUM);
	}
}
