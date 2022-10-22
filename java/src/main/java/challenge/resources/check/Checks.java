package challenge.resources.check;

import challenge.resources.domain.Resource;

import java.util.List;

public class Checks {

	/**
	 * This methods is here for the case #1 and passing `null` is here
	 * just to satisfy the ctor
	 */
	public static List<ResourceCheck> createAllChecks() {
		return List.of(new CheckOne(null), new CheckTwo(null));
	}

	public static List<ResourceCheck> createAllChecks(Resource r) {
		return List.of(new CheckOne(r), new CheckTwo(r));
	}
}
