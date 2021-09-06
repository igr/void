package v.o.i.d.facebook;

import java.util.Random;

public class Question1 {
	// given array may have the same values
	// find the max ones and return the random index of a max value
	// without a new allocation

	public static void main(String[] args) {
		System.out.println(findMaxIndexValue(new int[] {1,2,3,4,5}));   // 4
		System.out.println(findMaxIndexValue(new int[] {1,5,3,4,4,5}));   // 1 or 5
	}


	public static int findMaxIndexValue(int[] array) {
		int max = Integer.MIN_VALUE;
		int count = 0;

		for (int element : array) {
			if (element > max) {
				max = element;
				count = 0;
			}
			if (max == element) {
				count++;
			}
		}

		int rndResult = new Random().nextInt(count);

		int order = 0;
		for (int i = 0; i < array.length; i++) {
			if (max == array[i]) {
				if (order == rndResult) {
					return i;
				}
				order++;
			}
		}

		return -1;
	}
}
