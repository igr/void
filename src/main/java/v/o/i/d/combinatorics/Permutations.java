package v.o.i.d.combinatorics;

import v.o.i.d.Util;

/**
 * Permutations without repetition.
 */
public class Permutations {

	int[] perm;
	int count;
	int n;

	public Permutations(int n) {
		this.n = n;
		this.perm = new int[n];
		for (int i = 0; i < n; i++) {
			perm[i] = i;
		}
	}

	public void generate() {
		generate(n);
		System.out.println("total: " + count);
	}

	private void generate(int n) {
		if (n == 0) {
			count++;
			Util.print(perm);
			return;
		}
		for (int i = 0; i < n; i++) {
			Util.swap(perm, i, n - 1);
			generate(n - 1);
			Util.swap(perm, i, n - 1);
		}
	}

	public static void main(String[] args) {
		Permutations gen = new Permutations(4);
		gen.generate();
	}


}
