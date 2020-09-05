package v.o.i.d.lombok;

import lombok.AccessLevel;
import lombok.ToString;
import lombok.Value;
import lombok.With;
import lombok.experimental.NonFinal;

@Value
public class ValueExample {
	String name;
	@With(AccessLevel.PACKAGE) @NonFinal
	int age;
	double score;

	@ToString(includeFieldNames = true)
	@Value(staticConstructor = "of")
	public static class Exercise<T> {
		String name;
		T value;
	}
}
