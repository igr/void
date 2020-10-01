package challenge.cakethief;

import java.util.Arrays;

public class Theif {

	public static void main(String[] args) {
		CakeType[] cakeTypes = new CakeType[]{
				new CakeType(7, 160),
				new CakeType(3, 90),
				new CakeType(2, 15),
		};

		int capacity = 20;
		maxDuffelBagValue(cakeTypes, capacity);
	}

	public static void maxDuffelBagValue(CakeType[] cakeTypes, int capacity) {
		int smallestCakeNdx = indexOfSmallestWeightedCake(cakeTypes);
		int maxItems = capacity / smallestCakeNdx + 1;
		int[] arr = new int[cakeTypes.length];
		int maxPrice = 0;
		int[] maxArr = new int[cakeTypes.length];;

		while (true) {
			if (!nextStep(arr, maxItems)) {
				break;
			}
			//System.out.println(Arrays.toString(arr));
			int weight = calcWeight(cakeTypes, arr);
			//System.out.println("weight = " + weight);
			if (weight > capacity) {
				continue;
			}
			int price = calcPrice(cakeTypes, arr);
			//System.out.println("price = " + price);
			if (price > maxPrice) {
				maxPrice = price;
				System.arraycopy(arr, 0, maxArr, 0, arr.length);
			}
		}

		System.out.println(Arrays.toString(maxArr));
		System.out.println(calcWeight(cakeTypes, maxArr));
		System.out.println(calcPrice(cakeTypes, maxArr));
	}

	private static int calcWeight(CakeType[] cakeTypes, int[] arr) {
		int weight = 0;
		for (int i = 0; i < cakeTypes.length; i++) {
			CakeType cakeType = cakeTypes[i];
			weight += cakeType.weight * arr[i];
		}
		return weight;
	}

	private static int calcPrice(CakeType[] cakeTypes, int[] arr) {
		int price = 0;
		for (int i = 0; i < cakeTypes.length; i++) {
			CakeType cakeType = cakeTypes[i];
			price += cakeType.value * arr[i];
		}
		return price;
	}


	private static boolean nextStep(int[] arr, int maxItems) {
		int index = arr.length - 1;

		while (index >= 0) {
			arr[index]++;
			if (arr[index] >= maxItems) {
				arr[index] = 0;
				index--;
				continue;
			}
			return true;
		}
		return false;
	}

	private static int indexOfSmallestWeightedCake(CakeType[] cakeTypes) {
		int smallestWeight = Integer.MAX_VALUE;
		int smallNdx = -1;

		for (int i = 0; i < cakeTypes.length; i++) {
			CakeType cakeType = cakeTypes[i];
			if (cakeType.weight < smallestWeight) {
				smallestWeight = cakeType.weight;
				smallNdx = i;
			}
		}
		return smallNdx;
	}

}
