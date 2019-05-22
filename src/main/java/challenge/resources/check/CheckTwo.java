package challenge.resources.check;

import challenge.resources.domain.Health;
import challenge.resources.domain.Resource;

public class CheckTwo implements ResourceCheck {
	@Override
	public String id() {
		return "c2";
	}

	@Override
	public Health apply(Resource resource) {
		return Health.HEIGHT;
	}
}
