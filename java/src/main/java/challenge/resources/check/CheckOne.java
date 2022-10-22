package challenge.resources.check;

import challenge.resources.domain.Health;
import challenge.resources.domain.Resource;

public class CheckOne extends ResourceCheck {
	public CheckOne(Resource resource) {
		super(resource);
	}

	@Override
	public CheckHealth get() {
		return new CheckHealth(resource.getId(), "c1", Health.LOW);
	}
}
