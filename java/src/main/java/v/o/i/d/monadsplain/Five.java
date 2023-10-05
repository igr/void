package v.o.i.d.monadsplain;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class Five {
	public static class IntegerWithHistory {
		public final Integer value;
		public final String[] history;

		public IntegerWithHistory(Integer value, String... history) {
			this.value = value;
			this.history = history;
		}

		public static IntegerWithHistory wrap(Integer i) {
			return new IntegerWithHistory(i);
		}
	}

	public IntegerWithHistory calculate(IntegerWithHistory i, Function<Integer, IntegerWithHistory> operation) {
		final IntegerWithHistory a = operation.apply(i.value);
		return new IntegerWithHistory(
				a.value,
				join(i.history, join(i.history, a.history))
		);
	}

	public void run() {
		Function<Integer, IntegerWithHistory> addOne = i -> new IntegerWithHistory(i + 1, "addOne: " + i);
		Function<Integer, IntegerWithHistory> square = i -> new IntegerWithHistory(i * i, "square: " + i);
		IntegerWithHistory i = calculate(
				calculate(IntegerWithHistory.wrap(3), square),
				addOne);
		System.out.println(i.value);
		System.out.println(Arrays.toString(i.history));
	}

	public static void main(String[] args) {
		new Three().run();
	}

	private static String[] join(String[] array, String element) {
		return Stream.concat(Arrays.stream(array), Stream.of(element)).toArray(String[]::new);
	}

	private static String[] join(String[] array, String[] elements) {
		return Stream.concat(Arrays.stream(array), Stream.of(elements)).toArray(String[]::new);
	}

}
