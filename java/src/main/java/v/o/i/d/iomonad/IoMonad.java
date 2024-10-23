package v.o.i.d.iomonad;

import java.util.concurrent.Callable;
import java.util.function.Function;

public sealed interface IoMonad<T> {

	<R> IoMonad<R> map(Function<T, R> mapper);

	// Haskell's `>>=` operator (bind)
	<R> IoMonad<R> bind(Function<T, IoMonad<R>> mapper);

	// Haskell's `>>` operator (then)
	default <R> IoMonad<R> then(IoMonad<R> next) {
		return this.bind(ignored -> next);
	}

	T run() throws Exception;

	static <T> IoMonad<T> of(Callable<T> computation) {
		return new DefaultIoMonad<>(computation);
	}

	// just a syntax sugar
	static <T> IoMonad<T> of(Runnable computation) {
		return new DefaultIoMonad<>(() -> {
			computation.run();
			return null;
		});
	}

	final class DefaultIoMonad<T> implements IoMonad<T> {
		private final Callable<T> computation;

		DefaultIoMonad(Callable<T> computation) {
			this.computation = computation;
		}

		@Override
		public <R> IoMonad<R> map(Function<T, R> mapper) {
			return new DefaultIoMonad<>(() -> mapper.apply(computation.call()));
		}

		@Override
		public <R> IoMonad<R> bind(Function<T, IoMonad<R>> mapper) {
			return new DefaultIoMonad<>(() -> mapper.apply(computation.call()).run());
		}

		@Override
		public T run() throws Exception {
			return computation.call();
		}
	}
}
