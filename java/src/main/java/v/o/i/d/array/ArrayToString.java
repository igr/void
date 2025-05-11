package v.o.i.d.array;

import java.util.Arrays;

public class ArrayToString {

	public static void main(String[] args) {
		int[] groupId = new int[1];
		groupId[0] = 1;
		System.out.println("Group not found: " + groupId);
		System.out.println("Group not found: " + Arrays.toString(groupId));
	}
}
