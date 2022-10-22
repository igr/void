package challenge.resources.domain;

import challenge.resources.check.CheckHealth;

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

	public Score addHealth(CheckHealth checkHealth) {
		this.health = checkHealth.getHealth();
		healthMap.put(checkHealth.getCheckId(), health);
		return this;
	}
}
