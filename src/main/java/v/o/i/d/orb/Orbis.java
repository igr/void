package v.o.i.d.orb;

public class Orbis {

	// Returns the distance between the two closest numbers.
	static int distClosestNumbers(int[] numbers) {

		// Arrays.sort(numbers);
		// compare pairs of next elements


			int lowestDistance = Integer.MAX_VALUE;

		for (int i = 0; i < numbers.length; i++) {
			int firstNumber = numbers[i];

			// calculate the distance with the rest
			for (int j = i + 1; j < numbers.length; j++) {
				int secondNumber = numbers[j];

//				// dont do the distance of the same element
//				if (i == j) {
//					continue;
//				}

				final int distance = Math.abs(firstNumber - secondNumber);

				if (distance < lowestDistance) {
					lowestDistance = distance;
				}
			}
		}
		return lowestDistance;
	}

	public static void main(final String[] args) {
//		int[] testArray = {3, 9, 50, 15, 99, 7, 98, 65};
		int[] testArray = {3, 9, 50, 15, 7, 98, 65};

		int result = distClosestNumbers(testArray);

		System.out.println(result); // Expected result is 1 (the 2 closest numbers are 98 and 99)
	}
}
