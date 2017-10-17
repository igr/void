package v.o.i.d.search;

/**
 * To find whether a given key v is in an ordered array, it first compares v with the element at the middle position.
 * If v is smaller, then it must be in the first half of the array; if v is greater, then it must be in the
 * second half of the array.
 */
public class BinarySearch {

	/**
	 * This implementation of search uses a recursive binary-search procedure.
	 */
	static int searchR(int[] st, int l, int r, int v) {
		if (l > r) {
			return -1;
		}
		int m = (l + r) / 2;
		if (v == st[m]) {
			return m;
		}
		if (v < st[m]) {
			return searchR(st, l, m - 1, v);
		}
		return searchR(st, m + 1, r, v);
	}
	/**
	 * This implementation of search uses a iterative binary-search procedure.
	 */
	static int search(int[] st, int l, int r, int v) {
		while (true) {
			if (l > r) {
				return -1;
			}

			int m = (l + r) / 2;

			// We compute the middle of the interval by adding one-half of the size of the interval
			// to the left endpoint. Interpolation.
			// This calculation is based on the assumption that the key values are numerical and evenly distributed.
			// int m = l + (v - st[l]) * (r - l) / (st[r] - st[l]);

			if (v == st[m]) {
				return m;
			}
			if (v < st[m]) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
	}

	public static int search(int[] st, int v) {
		return search(st, 0, st.length, v);
	}

	public static void main(String[] args) {
		int[] a = new int[] {0,1,2,3,4,5,7,8,9,10,11};

		System.out.println(search(a, 3));
		System.out.println(search(a, 6));

	}

}
