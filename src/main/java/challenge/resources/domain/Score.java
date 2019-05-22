package challenge.resources.domain;

import java.util.HashMap;
import java.util.Map;

public class Score {
	private final Map<String, Health> healthMap = new HashMap<>();
	private Health health;

	public Health getHealth() {
		return health;
	}

	public Map<String, Health> getHealthMap() {
		return healthMap;
	}

	public Score addHealth(String checkName, Health health) {
		this.health = health;
		healthMap.put(checkName, health);
		return this;
	}
}
