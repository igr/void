package v.o.i.d.sort;
import v.o.i.d.Util;

/**
 * Insertion sort is a simple sorting algorithm that is relatively efficient for small lists and mostly-sorted lists,
 * and often is used as part of more sophisticated algorithms. It works by taking elements from the list one by one
 * and inserting them in their correct position into a new sorted list. In arrays, the new list and the remaining
 * elements can share the array's space, but insertion is expensive, requiring shifting all following elements
 * over by one. The insertion sort works just like its name suggests - it inserts each item into its proper place
 * in the final list. To save memory, most implementations use an in-place sort that works by moving the current
 * item past the already sorted items and repeatedly swapping it with the preceding item until it is in place.
 * Shell sort is a variant of insertion sort that is more efficient for larger lists.
 * This method is much more efficient than the bubble sort, though it has more constraints.
 *
 * Average: O(n + d), d is the number of inversions, which is O(n²)
 * Worst: O(n^2)
 * Memory: O(1)
 * Stable: YES.
 *
 * Insertion sort uses about N^2/4 comparisons and N^2/4 half-exchanges (moves) on the average, and twice that many at worst.
 */
public class Sort2_Insertion {

	public static void sort(int[] a) {
		sort(a, 0, a.length - 1);
	}

	public static void sort_faster(int[] a, int l, int r) {
		int i;
		// it puts the smallest element in the array into the first position so that that element can serve as a sentinel
		for (i = r; i > l; i--) {
			Util.compSwap(a, i - 1, i);
		}
		Util.print(a);
		for (i = l + 2; i <= r; i++) {
			int j = i;
			int v = a[i];
			while (v < a[j - 1]) {       //it terminates the inner loop when the element being inserted is in position
				a[j] = a[j - 1];
				j--;
			}
			a[j] = v;       // it does a single assignment, rather than an exchange, in the inner loop
			Util.print(a);
		}
	}

	/**
	 * Starts from 2nd element, go back towards the beginning of the array.
	 * If taken element is less then current, it will be replaced.
	 * Then it goes to the 3rd element, and again go back to the beginning of the array. 
	 */
	static void sort(int[] a, int l, int r) {
		for (int i = l + 1; i <= r; i++) {
			for (int j = i; j > l; j--) {
				Util.compSwap(a, j - 1, j);
				Util.print(a);
			}
			Util.print("--");
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
