package v.o.i.d.monadsplain;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class Four {
	public static class IntegerWithHistory {
		public final Integer value;
		public final String[] history;

		public IntegerWithHistory(Integer value, String[] history) {
			this.value = value;
			this.history = history;
		}

		public static IntegerWithHistory wrap(Integer i) {
			return new IntegerWithHistory(i, new String[] {});
		}
	}

	public IntegerWithHistory calculate(IntegerWithHistory i, Function<Integer, Integer> operation, String operationName) {
		return new IntegerWithHistory(
				operation.apply(i.value),
				join(i.history, operationName + ": " + i.value)
		);
	}

	public void run() {
		Function<Integer, Integer> addOne = i -> i + 1;
		Function<Integer, Integer> square = i -> i * i;
		IntegerWithHistory i = calculate(
				calculate(IntegerWithHistory.wrap(3), square, "square"),
				addOne, "addOne");
		System.out.println(i.value);
		System.out.println(Arrays.toString(i.history));
	}

	public static void main(String[] args) {
		new Three().run();
	}

	private static String[] join(String[] array, String element) {
		return Stream.concat(Arrays.stream(array), Stream.of(element)).toArray(String[]::new);
	}

}
