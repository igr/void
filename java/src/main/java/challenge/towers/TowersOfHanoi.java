package challenge.towers;

public class TowersOfHanoi {

	static int moves;

	/**
	 * Recursive solution.
	 * O(2^N) 
	 */
	static void hanoi(int height, int fromPole, int toPole, int withPole) {
		if (height >= 1) {
			hanoi(height - 1, fromPole, withPole, toPole);
			moveDisk(fromPole, toPole);
			hanoi(height - 1, withPole, toPole, fromPole);
		}
	}

	static void moveDisk(int fromPole, int toPole) {
		moves++;
		System.out.print(fromPole);
		System.out.print(toPole);
		System.out.print(((moves % 20) == 0) ? '\n' : ' ');
	}

	public static void main(String[] args) {
		hanoi(5, 1, 2, 3);
	}
}
