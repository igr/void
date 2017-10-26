package v.o.i.d.singleton;

public class EnumFactory {

	public SingletonEnum get() {
		return SingletonEnum.INSTANCE;
	}

	public enum SingletonEnum {
		INSTANCE
	}
}