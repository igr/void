package challenge.resources.domain;

import java.util.HashMap;
import java.util.Map;

public class Score {
	private final String resourceId;
	private final Map<String, Health> healthMap = new HashMap<>();
	private Health health;

	public Score(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public Health getHealth() {
		return health;
	}

	public Map<String, Health> getHealthMap() {
		return healthMap;
	}

	public void addHealth(String checkName, Health health) {
		this.health = health;
		healthMap.put(checkName, health);
	}
}
