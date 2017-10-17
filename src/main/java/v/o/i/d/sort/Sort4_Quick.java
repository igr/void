package v.o.i.d.sort;

import v.o.i.d.Util;

/**
 * Quicksort is a divide-and-conquer method for sorting. It works by partitioning an array into two parts,
 * then sorting the parts independently. As we shall see, the precise position of the partition depends on
 * the initial order of the elements in the input file. The crux of the method is the partitioning process,
 * which rearranges the array to make the following three conditions hold:
 * + The element a[i] is in its final place in the array for some i.
 * + None of the elements in a[l], ..., a[i-1] (on the left) is greater than a[i].
 * + None of the elements in a[i+1], ..., a[r] (on the right) is less than a[i].
 * <p/>
 * To partition an array, we choose an element, called a pivot, move all smaller elements before the pivot,
 * and move all greater elements after it.
 *
 * Average: O(n log(n))
 * Worst O(n^2)
 * Memory O(log(n))
 * Stable No 
 */
public class Sort4_Quick {

	public static void sort(int[] a) {
		sort(a, 0, a.length - 1);
	}

	public static void sort(int[] a, int l, int r) {
		if (r <= l) {
			return;
		}
		Util.print(a);
		int i = partition(a, l, r);
		Util.print(a);
		sort(a, l, i - 1);
		sort(a, i + 1, r);
	}

	/**
	 * We use the following general strategy to implement partitioning. First, we arbitrarily choose a[r] to be the
	 * partitioning element—the one that will go into its final position. Next, we scan from the left end of the array
	 * until we find an element greater than the partitioning element, and we scan from the right end of the array until
	 * we find an element less than the partitioning element. The two elements that stopped the scans are obviously out of
	 * place in the final partitioned array, so we exchange them.
	 *
	 * Continuing in this way, we ensure that no array elements to the left of the left index are greater than
	 * the partitioning element, and no array elements to the right of the right index are less than
	 * the partitioning element. When the scan indices cross, all that we need to do to complete the
	 * partitioning process is to exchange a[r] with the leftmost element of the right subfile (the element pointed to by the left index).
	 *
	 * The partitioning process divides a file into two subfiles that can be sorted independently. 
	 */
	private static int partition(int a[], int l, int r) {
		int i = l - 1, j = r;
		int v = a[r];
		while (true) {
			while (a[++i] < v) {
			}
			while (v < a[--j]) {
				if (j == l) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
			Util.swap(a, i, j);
		}
		Util.swap(a, i, r);
		return i;
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
