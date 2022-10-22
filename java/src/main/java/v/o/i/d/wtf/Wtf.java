package v.o.i.d.wtf;

/**
 * What does it print?
 */
public class Wtf {

	private final String name;

	private void prname() {
		System.out.println(name);
	}

	Wtf(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		new Wtf("main").doit();
	}

	private void doit() {
		new Wtf("doit") {
			void f() {
				prname();
			}
		}.f();
	}
}
