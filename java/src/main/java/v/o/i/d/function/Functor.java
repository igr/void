package v.o.i.d.function;

import java.util.function.Function;

public interface Functor<V> {

	//Functor<V> pure(V v);

	<R> Functor<R> map(Function<V, R> fn);

	V get();
}
