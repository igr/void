package challenge.resources.check;

import challenge.resources.domain.Health;

// used by case #2
public class CheckHealth {

	private final String checkId;
	private final Health health;
	private final String resourceId;

	public CheckHealth(String resourceId, String checkId, Health health) {
		this.resourceId = resourceId;
		this.checkId = checkId;
		this.health = health;
	}

	public String getResourceId() {
		return resourceId;
	}

	public String getCheckId() {
		return checkId;
	}

	public Health getHealth() {
		return health;
	}
}
