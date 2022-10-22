package v.o.i.d.equals;

import java.nio.CharBuffer;

public class StringVsCharSequenceEquals {

	public static void main(String[] args) {
		String one = "text";
		CharSequence two = CharBuffer.wrap(one.toCharArray());

		System.out.println(equals(one, two));
		System.out.println(equals(two, one));
		System.out.println(one.contentEquals(two));
	}

	private static boolean equals(CharSequence cs1, CharSequence cs2) {
		return cs1.equals(cs2);
	}

}
