package v.o.i.d.orb;

// JUST A SCKETCH
public class Orbis2 {

	void splitOddAndEvenNumbers(int[] a, int[] b) {

		int pointerA = 0;
		int pointerB = 0;

		while (true) {
			int elementA = a[pointerA];
			int elementB = b[pointerB];

			if (isOdd(elementA)) {
				if (isEven(elementB)) {
					pointerA++;
					pointerB++;
					continue;
				}

				pointerA++;
			} else {
				if (isOdd(elementB)) {
					// switch
					pointerA++;
					pointerB++;
					continue;
				}
				pointerB++;
			}
		}
	}



	private boolean isEven(int element) {
		return element % 2 == 0;
	}

	private boolean isOdd(int element) {
		return element % 2 == 1;
	}

	public static void main(String[] args) {
		int[] a = {1, 4, 7, 8, 10};
		int[] b = {2, 3, 5, 6, 9};

	}
}
