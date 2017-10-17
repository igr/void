package v.o.i.d.fibonacci;

public class Fibonacci {

	/**
	 * Recursive algorithm.
	 * O(2^n) exponential 2^0.694n
	 */
	public static long fib1(long n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return fib1(n - 1) + fib1(n - 2); 
	}

	/**
	 * Polynomial algorithm.
	 * O(n)
	 */
	public static long fib2(long n) {
		if (n == 0) {
			return 0;
		}
		long last = 0;
		long result = 1;
		for (long i = 1; i < n; i++) {
		    long temp = result;
			result += last;
			last = temp;
		}
		return result;
	}

	// ---------------------------------------------------------------- main

	public static void main(String[] args) {
		System.out.println(fib1(17));
		System.out.println(fib2(17));
	}
}
