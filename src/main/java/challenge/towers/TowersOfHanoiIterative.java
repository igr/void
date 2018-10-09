package challenge.towers;


public class TowersOfHanoiIterative {

	int[] disks;    // represents current position of the disks.
					// disk[i] == p means that disk i is on pole p(1,2,3)

	int moves;
	int n;

	public TowersOfHanoiIterative(int n) {
		this.n = n;
		this.moves = 1;
		disks = new int[n];
		for (int i = 0; i < disks.length; i++) {
			disks[i] = 1;
		}
	}

	public void run() {
		while(true) {
			if (moves == 1) {
				performTheFirstMove();
			} else {
				moveSmallestDiskClockwise();
			}
			if (isTheEnd()) {
				break;
			}
			performOnlyAvailableMoveNotInvolvingSmallestDisk();
			if (isTheEnd()) {
				break;
			}
		}
	}

	private void performTheFirstMove() {
		if (n % 2 == 0) {
			disks[n - 1] = 2;
			out(1, 2);
		} else {
			disks[n - 1] = 3;
			out(1, 3);
		}
	}

	private void performOnlyAvailableMoveNotInvolvingSmallestDisk() {
		int smallestPole = disks[n - 1];
		int poleA, poleB;
		switch (smallestPole) {
			case 1: poleA = 2; poleB = 3; break;
			case 2: poleA = 3; poleB = 1; break;
			case 3: poleA = 1; poleB = 2; break;
			default:
				throw new IllegalStateException("The smallest disk is not on existing pole.");
		}
		int diskA = findFirstDiskOnPole(poleA);
		int diskB = findFirstDiskOnPole(poleB);
		if (diskA < diskB) {
			disks[diskB] = poleA;
			out(poleB, poleA);
		} else if (diskA > diskB) {
			disks[diskA] = poleB;
			out(poleA, poleB);
		} else {
			throw new IllegalStateException("Two disks of the same size on two poles: " + poleA + " and " + poleB);
		}
	}

	private int findFirstDiskOnPole(int pole) {
		for (int i = n - 1; i >= 0; i--) {
			if (disks[i] == pole) {
				return i;
			}
		}
		return -1;
	}

	private boolean isTheEnd() {
		moves++;
		for (int disk : disks) {
			if (disk != 3) {
				return false;
			}
		}
		return true;
	}

	private void moveSmallestDiskClockwise() {
		int before = disks[n - 1];
		disks[n - 1]++;
		if (disks[n - 1] > 3) {
			disks[n - 1] =  1;
		}
		out(before, disks[n - 1]);
	}

	private void out(int from, int to) {
		System.out.println(moves + "\t" + from + "->" + to);
	}

	public static void main(String[] args) {
		TowersOfHanoiIterative tohi = new TowersOfHanoiIterative(4);
		tohi.run();
	}
}
