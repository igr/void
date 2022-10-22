package v.o.i.d.combinatorics;

/**
 * Combinations may be done using permutations: each 1 represents occurrence.
 */
public class Combinations {

	int[] comb;

	public Combinations(int n) {
		comb = new int[n];
		for (int i = 0; i < n; i++) {
			comb[i] = i;
		}
	}

	public void comb2(String s) {
		comb2("", s);
	}

	private void comb2(String prefix, String s) {
		System.out.println(prefix);
		for (int i = 0; i < s.length(); i++) {
			comb2(prefix + s.charAt(i), s.substring(i + 1));
		}
	}

	public static void main(String[] args) {
		Combinations c = new Combinations(4);
		c.comb2("123");
	}

}