package v.o.i.d.monadsplain;

public class Two {
	public static class IntegerWithHistory {
		public final Integer value;
		public final String[] messages;

		public IntegerWithHistory(Integer value, String[] messages) {
			this.value = value;
			this.messages = messages;
		}
	}

	public IntegerWithHistory square(Integer i) {
		return new IntegerWithHistory(
				i * i,
				new String[] {"square: " + i}
		);
	}
	public IntegerWithHistory addOne(Integer i) {
		return new IntegerWithHistory(
				i + 1,
				new String[] {"addOne: " + i}
		);
	}

}
