package v.o.i.d.worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWorkload {

	private static final Random rnd = new Random();

	public static List<Integer> randomWorkList() {
		final List<Integer> values = new ArrayList<>();
		for (int i = 0; i < rnd.nextInt(50); i++) {
			values.add(rnd.nextInt());
		}
		return values;
	}

	public static Integer randomWork() {
		return rnd.nextInt();
	}
}
