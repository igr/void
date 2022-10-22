/**
 * Even just simple counting in parallel is not simple as it sounds.
 * Naive counting gives invalid results.
 * Naive and synchronized works, but it is slower.
 * AtomicLong works, it's faster.
 * LongAdder is the fastest.
 *
 * http://psy-lob-saw.blogspot.rs/2013/06/java-concurrent-counters-by-numbers.html
 *
 * http://blog.takipi.com/java-8-longadders-the-fastest-way-to-add-numbers-concurrently/
 * --- Concurrent Adders clean house with a 60-100% performance boost over atomic integers.
 * --- Adding threads didn’t make much of a difference, except when locking.
 * --- Notice the huge performance penalty you get for using synchronized or RW-locks – one order or even two orders of magnitude slower!
 *
 * An AtomicLong holds a single number which every thread will attempt to update.
 * Because of this, as you have already found, only one thread can update this value at a time.
 * The advantage, though, is that the value will always be up-to-date when a get is called, as there
 * will be no adds in progress at that time.
 *
 * A LongAdder, on the other hand, is made up of multiple values, and each value will be updated
 * by a subset of the threads. This results in less contention when updating the value, however
 * it is possible for sum to have an incomplete value if done while an add is in progress,
 * similar to the scenario you described.
 *
 * LongAdder is recommended for those cases where you will be doing a bunch of adds in
 * parallel followed by a sum at the end.
 */
package v.o.i.d.parallelAdder;