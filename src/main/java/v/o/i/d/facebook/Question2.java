package v.o.i.d.facebook;

public class Question2 {

	/*
	Given tree:

	   |
	   5
	 /  \
	4    3
   / \  / \
   9  1 2  7
    \
	 8

	 |
	 -> 9 <-> 8 <-> 4 <-> 1 <-> 5 <-> 2 <-> 3 <-> 7 <-
	 -------------------------------------------------

	Make a double linked-list from a tree.
	Without creating a new structure.

	*/

	// given class
	public static class Node {
		public Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
		public int value;
		public Node left;
		public Node right;
	}

	public static void main(String[] args) {
		var node8 = new Node(8, null, null);
		var node9 = new Node(9, null, node8);
		var node1 = new Node(1, null, null);
		var node4 = new Node(4, node9, node1);

		var node2 = new Node(2, null, null);
		var node7 = new Node(7, null, null);
		var node3 = new Node(3, node2, node7);

		var node5 = new Node(5, node4, node3);

		walk(node5);
	}

	private static void walk(Node node) {
		if (node.left != null) {
			walk(node.left);
		}

		System.out.println(node.value);
		// ?

		if (node.right != null) {
			walk(node.right);
		}
	}
}
