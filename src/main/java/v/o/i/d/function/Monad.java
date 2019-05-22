package v.o.i.d.function;

import java.util.function.Function;

public interface Monad<V> extends Functor<V> {

	<R> Monad<R> bind(Function<V, Monad<R>> fn);

	@Override
	<R> Monad<R> map(Function<V, R> fn);
}