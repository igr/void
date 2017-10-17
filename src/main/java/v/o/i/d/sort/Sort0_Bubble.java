package v.o.i.d.sort;

import v.o.i.d.Util;

/**
 * Keep passing through the file, exchanging adjacent elements that are out of order, continuing until the file is sorted.
 * Bubble sort's prime virtue is that it is easy to implement, but whether it is actually easier to implement than insertion
 * or selection sort is arguable. Bubble sort generally will be slower than the other two methods, but we consider it
 * briefly for the sake of completeness.
 * <p/>
 * The algorithm starts at the beginning of the data set. It compares the first two elements, and if the first is greater
 * than the second, it swaps them. It continues doing this for each pair of adjacent elements to the end of the data set.
 * It then starts again with the first two elements, repeating until no swaps have occurred on the last pass. While simple,
 * this algorithm is highly inefficient and is rarely used except in education.
 * <p/>
 * Average: O(n^2)
 * Worst: O(n^2)
 * Memory: O(1)
 * Stable: YES
 *
 * Bubble sort uses about N^2/2 comparisons and N^2/2 exchanges on the average and in the worst case.
 */
public class Sort0_Bubble {

	public static void sort(int[] a) {
		sort(a, 0, a.length - 1);
	}

	public static void sort(int[] a, int l, int r) {
		for (int i = l; i < r; i++) {
			for (int j = r; j > i; j--) {
				Util.compSwap(a, j - 1, j);
			}
			Util.print(a);
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
