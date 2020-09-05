package v.o.i.d.singleton;

/**
 * Double-Checked Locking idiom (DCL).
 *
 * This construction does not work properly for two reasons.
 *
 * One could think that after "check 1" had succeeded, the Singleton instance is properly initialized, and we can return it.
 * That is not correct: the Singleton contents are only fully visible to the constructing thread!
 * There are no guarantees that you will see Singleton contents properly in other threads, because you are racing
 * against the initializer thread. Once again, even if you have observed the non-null instance,
 * it does not mean you observe its internal state properly. In JMM-ese speak, there is no happens-before
 * between the initializing stores in Singleton constructor, and the reads of Singleton fields.
 *
 * Notice that we do several reads of instance in this code, and at least "read 1" and "read 3" are the reads
 * without any synchronization — that is, those reads are racy. One of the intents of the Java Memory Model is to
 * allow reorderings for ordinary reads, otherwise the performance costs would be prohibitive. Specification-wise,
 * as mentioned in happens-before consistency rules, a read action can observe the unordered write via the race.
 * This is decided for each read action, regardless what other actions have already read the same location.
 * In our example, that means that even though "read 1" could read non-null instance, the code then moves on to
 * returning it, then it does another racy read, and it can read a null instance, which would be returned!
 *
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * Unlike most other languages, Java defines its relationship to the underlying hardware through a formal memory model
 * that is expected to hold on all Java platforms, enabling Java's promise of "Write Once, Run Anywhere."
 * Compilers, however, can generate instructions in a different order from the obvious interpretation the program
 * suggests and store variables in registers instead of memory; processors may execute instructions in parallel
 * or out of order; and caches may vary the order in which writes commit to main memory. The JMM says that all
 * of these various reorderings and optimizations are acceptable, so long as the environment maintains as-if-serial
 * semantics -- that is, so long as you achieve the same result as you would have if the instructions were executed
 * in a strictly sequential environment.
 *
 * Java treats each thread as if it runs on its own processor with its own local memory, each talking to and
 * synchronizing with a shared main memory. Even on a single-processor system, that model makes sense because of the
 * effects of memory caches and the use of processor registers to store variables. When a thread modifies a location
 * in its local memory, that modification should eventually show up in the main memory as well, and the JMM defines
 * the rules for when the JVM must transfer data between local and main memory.
 *
 * Java's primary tool for rendering interactions between threads predictably is the `synchronized` keyword.
 * Many programmers think of synchronized strictly in terms of enforcing a mutual exclusion semaphore (mutex) to prevent
 * execution of critical sections by more than one thread at a time. Unfortunately, that intuition does not fully
 * describe what synchronized means.
 *
 * The semantics of synchronized do indeed include mutual exclusion of execution based on the status of a semaphore,
 * but they also include rules about the synchronizing thread's interaction with main memory. In particular, the
 * acquisition or release of a lock triggers a memory barrier -- a forced synchronization between the thread's local
 * memory and main memory. (Some processors -- like the Alpha -- have explicit machine instructions for performing
 * memory barriers.) When a thread exits a synchronized block, it performs a write barrier -- it must flush out
 * any variables modified in that block to main memory before releasing the lock. Similarly, when entering a
 * synchronized block, it performs a read barrier -- it is as if the local memory has been invalidated, and it must
 * fetch any variables that will be referenced in the block from main memory.
 *
 * DCL relies on an unsynchronized use of the instance field.
 * Imagine that thread A is inside the synchronized block, executing the statement #1; while thread B is just entering
 * get(). Consider the effect on memory of this initialization. Memory for the new Singleton object will be allocated;
 * the constructor for Singleton will be called, initializing the member fields of the new object; and the field
 * resource of Singleton will be assigned a reference to the newly created object.
 *
 * However, since thread B is not executing inside a synchronized block, it may see these memory operations in a
 * different order than the one thread A executes! It could be the case that B sees these events in the following order
 * (and the compiler is also free to reorder the instructions like this):
 * + allocate memory
 * + assign reference to instance,
 * + call constructor.
 * Suppose thread B comes along after the memory has been allocated and the instance field is set, but before the
 * constructor is called. It sees that resource is not null, skips the synchronized block, and returns a reference
 * to a partially constructed Singleton! Needless to say, the result is neither expected nor desired.
 *
 * Suppose thread A has completed initializing the Singleton and exits the synchronized block as thread B enters
 * get(). Now the Singleton is fully initialized, and thread A flushes its local memory out to main memory.
 * The instance's fields may reference other objects stored in memory through its member fields, which will
 * also be flushed out. While thread B may see a valid reference to the newly created Singleton, because it
 * didn't perform a read barrier, it could still see stale values of resource's member fields.
 *
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * Because there is no locking if the field is already initialized, it is critical that the field be declared volatile
 * (Item 66) [J. Bloch, "Effective Java", Item 71]
 *
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * To show how this occurs, consider the following pseudo code for the line: instance = new Singleton();
 *
 * mem = allocate();            // Allocate memory for Singleton object.
 * instance = mem;              // Note that instance is now non-null, but has not been initialized.
 * ctorSingleton(instance);     // Invoke constructor for Singleton passing instance.
 *
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * The most obvious reason it doesn't work it that the writes that initialize the Helper object and the write to the
 * helper field can be done or perceived out of order. Thus, a thread which invokes getHelper() could see a non-null
 * reference to a helper object, but see the default values for fields of the helper object, rather than the values
 * set in the constructor.
 *
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * One of the key issues with the Java Memory Model is the concept of visibility. If Thread 1 updates a variable
 * someValue = 42 then when would the other threads (e.g. Thread 2) see this update? It turns out that Thread 1’s
 * update will not be seen immediately by other threads. In fact, there is no guarantee as to how quickly a change
 * in this variable will be seen by other threads at all. It could be 100 ns, 1 ms, 1 s, or even 10 years in theory.
 * There are performance reasons for isolating the java memory view between threads. Because each thread can have
 * its own memory view, the level of parallelism will be much higher than if threads were supposed to share
 * and guarantee the same memory model. Some of the benefits with relaxed visibility are that it allows:
 * + The compiler to reorder instructions in order to execute more efficiently
 * + The compiler to cache variables in CPU registers
 * + The CPU to defer flushing of writes to main memory
 * + Old entries in reading processors’ caches to be used
 * Also, If a field is declared volatile, reads and writes are always made via main memory. Thus, updates to the
 * field are seen by other threads at the cost of performance.
 */
public class UnsafeDCLFactory {
	private Singleton instance;

	public Singleton get() {
		if (instance == null) {                         // read 1, check 1
			synchronized (this) {
				if (instance == null) {                 // read 2, check 2
					instance = new Singleton();       // statement #1

					// mem = allocate();              // Allocate memory for Singleton object.
                    // instance = mem;                // instance is now non-null, but has not been initialized.
                    // ctorSingleton(instance);       // Invoke constructor for Singleton passing instance.
				}
			}
		}
		return instance;                              // read 3
	}
}
