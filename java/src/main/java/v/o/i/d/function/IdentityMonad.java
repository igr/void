package v.o.i.d.function;

import java.util.Objects;
import java.util.function.Function;

public class IdentityMonad<V> implements Monad<V> {

	private final V value;

	protected IdentityMonad(V value) {
		this.value = Objects.requireNonNull(value);
	}

	/**
	 * A type lift from some type into the monad context.
	 * Also called Unit.
	 */
	public static <T> IdentityMonad<T> of(T value) {
		return new IdentityMonad<>(value);
	}

	/**
	 * FlatMap.
	 * Monad chaining.
	 */
	@Override
	public <R> Monad<R> bind(Function<V, Monad<R>> fn) {
		return fn.apply(value);
	}

	/**
	 * Functor mapping.
	 * map(fn) == bind(unit).
	 */
	@Override
	public <R> Monad<R> map(Function<V, R> fn) {
		return new IdentityMonad<>(fn.apply(value));
	}

	/**
	 * Unwrapping the type from the context.
	 * Join, Flatten.
	 */
	@Override
	public V get() {
		return value;
	}
}
