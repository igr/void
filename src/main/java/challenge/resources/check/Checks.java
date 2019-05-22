package challenge.resources.check;

import java.util.List;

public class Checks {
	public static List<ResourceCheck> createAllChecks() {
		return List.of(new CheckOne(), new CheckTwo());
	}
}
