package v.o.i.d.combinatorics;


import v.o.i.d.Util;

/**
 * Generate all permutations with repetition of a given length for a given alphabet, implemented iteratively.
 * The number of permutations with repetition is n^l where n is the length of the alphabet and l the length
 * of the permutations to generate.
 */
public class PermutationsWithRepetition {

	int n;      // number of alphabets 
	int l;      // permutation length
	int[] perm;
	int count = 1;

	public PermutationsWithRepetition(int n, int l) {
		this.n = n;
		this.l = l;
		this.perm = new int[l];
	}

	/**
	 * Generates next permutation until the end when <code>false</code> is returned.
	 */
	public boolean nextPermutation() {
		count++;
		int i = l - 1;
		while (i >= 0) {
			perm[i]++;
			if (perm[i] == n) {
				perm[i] = 0;
				i--;
				continue;
			}
			return true;
		}
		return false;
	}

	public void print() {
		Util.print(perm);
	}

	public static void main(String[] args) {
		PermutationsWithRepetition gen = new PermutationsWithRepetition(4, 3);
		do {
			gen.print();
		} while (gen.nextPermutation());

		gen = new PermutationsWithRepetition(2, 4);
		do {
			gen.print();
		} while (gen.nextPermutation());

	}
}
