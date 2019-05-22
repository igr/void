package challenge.resources.check;

import challenge.resources.domain.Health;
import challenge.resources.domain.Resource;

public class CheckOne implements ResourceCheck {
	@Override
	public String id() {
		return "c1";
	}

	@Override
	public Health apply(Resource resource) {
		return Health.LOW;
	}
}
