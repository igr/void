package v.o.i.d.sort;


import v.o.i.d.Util;

/**
 * It improves upon bubble sort and insertion sort by moving out of order elements more than one position at a time.
 * One implementation can be described as arranging the data sequence in a two-dimensional array and then sorting the
 * columns of the array using insertion sort. Although this method is inefficient for large data sets, it is one of
 * the fastest algorithms for sorting small numbers of elements (sets with less than 1000 or so elements). Another
 * advantage of this algorithm is that it requires relatively small amounts of memory.
 *
 * The idea is to rearrange the file to give it the property that taking every hth element (starting anywhere) yields
 * a sorted file. Such a file is said to be h-sorted. Put another way, an h-sorted file is h independent sorted files,
 * interleaved together. By h-sorting for some large values of h, we can move elements in the array long distances and
 * thus make it easier to h-sort for smaller values of h. Using such a procedure for any sequence of values of h
 * that ends in 1 will produce a sorted file: that is the essence of shellsort.
 *
 * Knuth found that the functional forms N(log N)^2 and N^1.25 both fit the data reasonably well,
 * Memory: O(1)
 * Stable: NO 
 */
public class Sort3_Shell {

	public static void sort(int[] a) {
		sort(a, 0, a.length - 1);
	}

	static void sort(int[] a, int l, int r) {
		int h = 1;
		while(h <= (r - l) / 9) {
			h = 3 * h + 1;
		}
		Util.print("h=" + h);
		for (; h > 0; h /= 3) {
			System.out.println(">" + h);
			for (int i = l + h; i <= r; i++) {
				int j = i;
				int v = a[i];
				while ((j >= (l + h)) && (v < a[j - h])) {
					a[j] = a[j - h];
					j -= h;
				}
				a[j] = v;
				Util.print(a);
			}
		}
	}


	public static void main(String[] args) {
		int[] a = new int[]{7, 3, 1, 6, 4, 2, 9, 8, 5, 17, 13, 11, 16, 14, 12, 19, 18, 15};
		Util.print(a);
		System.out.println("--------------------");
		sort(a);
		System.out.println("--------------------");
		Util.print(a);
	}

}
