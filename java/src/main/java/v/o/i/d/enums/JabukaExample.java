package v.o.i.d.enums;

public class JabukaExample {

	sealed interface Apple {
		record GrannySmith() implements Apple {}
		record GoldenDel() implements Apple {}
		record PinkLady() implements Apple {}
	}
	public static void main(String[] args) {
		Apple apple = new Apple.GrannySmith();
		System.out.println(apple);
	}

}
