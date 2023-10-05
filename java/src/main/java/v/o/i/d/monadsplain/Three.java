package v.o.i.d.monadsplain;

import java.util.Arrays;
import java.util.stream.Stream;

public class Three {

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

	public IntegerWithHistory square(IntegerWithHistory i) {
		return new IntegerWithHistory(
				i.value * i.value,
				join(i.history, "square: " + i.value)
		);
	}

	public IntegerWithHistory addOne(IntegerWithHistory i) {
		return new IntegerWithHistory(
				i.value + 1,
				join(i.history, "addOne: " + i.value)
		);
	}

	public void run() {
		IntegerWithHistory i = addOne(square(IntegerWithHistory.wrap(3)));
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
