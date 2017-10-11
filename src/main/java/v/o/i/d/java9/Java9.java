package v.o.i.d.java9;

import v.o.i.d.java9.lang.AnInterface;

public class Java9 {

	public static void main(String[] args) {
		new AnInterface(){}.say();

		class X implements AnInterface {}
		new X().say();
	}

}
