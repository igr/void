package v.o.i.d.java9.lang;

public interface AnInterface {

	default void say() {
		hello();
	}

	private void hello() {
		System.out.println("Java9 allows private methods in interfaces.");
	}
}
