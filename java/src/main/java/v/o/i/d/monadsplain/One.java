package v.o.i.d.monadsplain;

public class One {

	public Integer square(Integer i) {
		return i * i;
	}
	public Integer addOne(Integer i) {
		return i + 1;
	}

	public void run() {
		System.out.println(addOne(square(3)));
	}
	public static void main(String[] args) {
		new One().run();
	}
}
