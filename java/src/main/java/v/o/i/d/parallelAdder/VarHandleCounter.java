package v.o.i.d.parallelAdder;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class VarHandleCounter {
	private long counter = 0;

	final VarHandle counterVarHandle;

	public VarHandleCounter() {
		try {
			counterVarHandle = MethodHandles
				.lookup()
				.in(VarHandleCounter.class)
				.findVarHandle(VarHandleCounter.class, "counter", long.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void increment() {
		counterVarHandle.getAndAdd(this, 1);
	}

	public long count() {
		return (long) counterVarHandle.get(this);
	}

}
