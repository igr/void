package v.o.i.d.towers;

/**
 * The Tower of Hanoi (sometimes referred to as the Tower of Brahma or the End of the World Puzzle)
 * was invented by the French mathematician, Edouard Lucas, in 1883. He was inspired by a legend that
 * tells of a Hindu temple where the pyramid puzzle might have been used for the mental discipline
 * of young priests. Legend says that at the beginning of time the priests in the temple were given a
 * stack of 64 gold disks, each one a little smaller than the one beneath it. Their assignment was to
 * transfer the 64 disks from one of the three poles to another, with one important rule: large disk
 * could never be placed on top of a smaller one. The priests worked very efficiently, day and night.
 * When they finished their work, the myth said, the temple would crumble into dust and the world would vanish.
 *
 * The number of separate transfers of single disks the priests must make to transfer the tower is 2 to the 64th minus 1,
 * or 18,446,744,073,709,551,615 moves! If the priests worked day and night, making one move every second it would take
 * slightly more than 580 billion years to accomplish the job!
 *
 */
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
