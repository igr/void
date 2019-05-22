package challenge.resources.check;

import challenge.resources.domain.Health;
import challenge.resources.domain.Resource;

import java.util.function.Function;

public interface ResourceCheck extends Function<Resource, Health> {
	String id();
}
