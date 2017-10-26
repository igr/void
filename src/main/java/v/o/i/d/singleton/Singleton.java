package v.o.i.d.singleton;

/**
 * <b>Singleton</b> is an object that has only a single instance at every point of program life-cycle in some context.
 * <p>
 * <b>Singleton Factory</b> is an object that maintains Singletons.
 *
 * A reasonable SingletonFactory has a few properties:
 * + It provides the public API for getting a Singleton instance.
 * + It is thread-safe. No matter how many threads are requesting a Singleton, all threads will get the same Singleton instance, regardless of the current state.
 * + It is lazy. One can argue about this, but non-lazy factories are not interesting for our discussion. Singleton initialization should happen with the first request for a Singleton, not when Singleton class is initialized. If no one wants a Singleton instance, it should not be instantiated.
 * + It is efficient. The overheads for managing a Singleton state should be kept at minimum.
 *
 * See:
 * 1. {@link SynchronizedCLFactory}
 * 2. {@link UnsafeDCLFactory}
 * 3. {@link SafeDCLFactory}
 * 4. {@link HolderFactory} & {@link EnumFactory}
 * 5. {@link SafeDCLFactory}
 */
public class Singleton {
}
