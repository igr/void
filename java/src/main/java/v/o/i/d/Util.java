package v.o.i.d;

/**
 * Some common tools.
 */
public class Util {

	public static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	/**
	 * Does the {@link #swap(int[], int, int)} if a[j] is less than a[i].
	 */
	public static void compSwap(int[] a, int i, int j) {
		if (a[j] < a[i]) {
			swap(a, i, j);
		}
	}

	public static void print(String s) {
		System.out.println(s);
	}

	public static void print(int[] a) {
		for (int i: a) {
			System.out.print(i + " ");
		}
		System.out.print("\n");
	}

	public static void print(int h) {
		System.out.println(h);
	}
}
