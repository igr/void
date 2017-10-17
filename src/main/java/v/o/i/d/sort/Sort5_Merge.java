package v.o.i.d.sort;

import v.o.i.d.Util;

/**
 * Top-Bottom
 *
 * Average: O(n log n)
 * Worst: O(n log n)
 * Memory: O(n)
 * Stable: yes
 */
public class Sort5_Merge {
	static int[] aux;

	public static void sort(int[] a) {
		aux = new int[a.length];
		sort(a, 0, a.length - 1);
	}

	public static void sort(int[] a, int l, int r) {
		if (r <= l) {
			return;
		}
		int m = (r + l) / 2;
		sort(a, l, m);
		sort(a, m + 1, r);
		merge(a, l, m, r);
	}

	/**
	 * This program merges without using sentinels by copying the second array into the auxiliary array aux in reverse
	 * order back to back with the first (putting aux in bitonic order). The first for loop moves the first array and
	 * leaves i at l, ready to begin the merge. The second for loop moves the second array, and leaves j at r. Then,
	 * in the merge (the third for loop), the largest element serves as the sentinel in whichever array it is. The
	 * inner loop of this program is short (move to aux, compare, move back to a, increment i or j, increment and test k).
	 */
	static void merge(int[] a, int l, int m, int r) {
		int i, j;
		for (i = m + 1; i > l; i--) {
			aux[i - 1] = a[i - 1];
		}
		for (j = m; j < r; j++) {
			aux[r + m - j] = a[j + 1];
		}
		for (int k = l; k <= r; k++) {
			if (aux[j] < aux[i]) {
				a[k] = aux[j--];
			} else {
				a[k] = aux[i++];
			}
		}
	}


	public static void main(String[] args) {
		int[] a = new int[]{7, 3, 1, 6, 4, 2, 9, 8, 5};
		Util.print(a);
		System.out.println("--------------------");
		sort(a);
		System.out.println("--------------------");
		Util.print(a);
	}
}
