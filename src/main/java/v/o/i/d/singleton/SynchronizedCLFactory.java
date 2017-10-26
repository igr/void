package v.o.i.d.singleton;

/**
 * Not efficient as we do synchronize on every access.
 */
public class SynchronizedCLFactory {

	private Singleton instance;

	public Singleton get() {
		synchronized (this) {
			if (instance == null) {
				instance = new Singleton();
			}
			return instance;
		}
	}
}
