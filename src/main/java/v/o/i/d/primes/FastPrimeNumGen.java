package v.o.i.d.primes;

public final class FastPrimeNumGen {

	public static final int TOTAL = 10000000;

	public static int[] arr;

	public static void main(String args[]) throws Exception {
		long start = System.currentTimeMillis();
		SimpleFileAccess file = new SimpleFileAccess("out.txt");
		arr = new int[TOTAL + 1];

		file.open();
		file.println(1);
		file.println(2);

		for (int i = 3; i < TOTAL; i += 2) {
			if (arr[i] == 0) {
				file.println(i);	// prime founded
				for (int j = i + i; j < TOTAL; j += i) {
					arr[j] = 1;
				}
			}
		}
		file.close();

		System.out.println("done in " + (System.currentTimeMillis() - start) + "ms.");
	}
}
