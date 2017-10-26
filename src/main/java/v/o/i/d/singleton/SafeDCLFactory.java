package v.o.i.d.singleton;

/**
 * Volatile write and volatile reads of instance yield the actions bound in synchronizes-with order,
 * and therefore form happens-before. That means the actions preceding the volatile store (that is, the
 * actions in constructors) precede any actions after reading the instance. In other words, those threads
 * that called get() will observe a fully-constructed Singleton.
 *
 * Volatile reads and writes of instance yield synchronization actions that are totally ordered,
 * and consistent with program order. Therefore two consecutive reads of instance are
 * guaranteed to see the same value, in the absence of intervening write to instance.
 */
public class SafeDCLFactory {
	private volatile Singleton instance;

	public Singleton get() {
		if (instance == null) {         // check 1
			synchronized(this) {
				if (instance == null) { // check 2
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
