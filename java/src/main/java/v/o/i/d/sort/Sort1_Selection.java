package v.o.i.d.sort;

import v.o.i.d.Util;

/**
 * One of the simplest sorting algorithms works as follows: First, find the smallest element in the array,
 * and exchange it with the element in the first position. Then, find the second smallest element and exchange
 * it with the element in the second position. Continue in this way until the entire array is sorted.
 *
 * Selection sort is a simple sorting algorithm that improves on the performance of bubble sort. It works by first
 * finding the smallest element using a linear scan and swapping it into the first position in the list, then finding
 * the second smallest element by scanning the remaining elements, and so on. Selection sort is unique compared to
 * almost any other algorithm in that its running time is not affected by the prior ordering of the list:
 * it performs the same number of operations because of its simple structure. Selection sort also requires only
 * N swaps, and hence just Θ(n) memory writes, which is optimal for any sorting algorithm. Thus it can be very
 * attractive if writes are the most expensive operation, but otherwise selection sort will usually be outperformed
 * by insertion sort or the more complicated algorithms.
 *
 * It takes about as long to run selection sort for a file that is already in order, or for a file with all
 * keys equal, as it does for a randomly ordered file.
 * 
 * Average: O(n^2)
 * Worst: O(n^2)
 * Memory: O(1)
 * Stable: NO
 *
 * Selection sort uses about N^2/2 comparisons and N exchanges.
 *
 * It is the method of choice for sorting files with huge items and small keys. For such applications,
 * the cost of moving the data dominates the cost of making comparisons, and no algorithm can sort a file
 * with substantially less data movement than selection sort. 
 */
public class Sort1_Selection {

	public static void sort(int[] a) {
		sort(a, 0, a.length - 1);
	}
	static void sort(int[] a, int l, int r) {
		for (int i = l; i < r; i++) {
			int min = i;
			for (int j = i + 1; j <= r; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}
			Util.swap(a, min, i);
			Util.print(a);
		}
	}

	public static void main(String[] args) {
		int[] a = new int[] {7, 3, 1, 6, 4, 2, 9, 8, 5};
		Util.print(a);
		System.out.println("--------------------");
		sort(a);
		System.out.println("--------------------");
		Util.print(a);
	}


}
