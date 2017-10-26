package v.o.i.d.singleton;


/**
 * Class initialization is done under the lock, as per JLS 12.4.2.
 * Class initialization lock provides the mutual exclusion during the class initialization,
 * that is, only a single thread can initialize the static fields.
 *
 * It's also lazy.
 */
public class HolderFactory {
	public static Singleton get() {
		return Holder.instance;
	}

	private static class Holder {
		public static final Singleton instance = new Singleton();
	}
}