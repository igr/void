package v.o.i.d.trees;

import v.o.i.d.Util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {

	public static void traverseRecursive(Node h) {
		if (h == null) {
			return;
		}
		// preorder
		h.visit();
		traverseRecursive(h.left);
		traverseRecursive(h.right);

		// inorder
//		traverseRecursive(h.left);
//		h.visit();
//		traverseRecursive(h.right);

		// postorder
//		traverseRecursive(h.left);
//		traverseRecursive(h.right);
//		h.visit();

		
	}

	public static void traverseStack(Node h) {
		Stack<Node> s = new Stack<>();
		s.push(h);
		while (!s.empty()) {
			h = s.pop();

			// preorder
			h.visit();
			if (h.right != null) {
				s.push(h.right);
			}
			if (h.left != null) {
				s.push(h.left);
			}
		}
	}


	public static void traverseStackLevelOrder(Node h) {
		Queue<Node> s = new LinkedList<>();
		s.add(h);
		while (!s.isEmpty()) {
			h = s.poll();

			// preorder
			h.visit();
			if (h.right != null) {
				s.add(h.right);
			}
			if (h.left != null) {
				s.add(h.left);
			}
		}
	}


	// ---------------------------------------------------------------- size


	public static int count(Node h) {
		if (h == null) {
			return 0;
		}
		return count(h.left) + count(h.right) + 1;
	}

	public static int height(Node h) {
		if (h == null) {
			return -1;
		}
		int u = height(h.left), v = height(h.right);
		if (u > v) {
			return u + 1;
		} else {
			return v + 1;
		}
	}

	// ---------------------------------------------------------------- traversal

	public static void main(String[] args) {
		Node root = TreeFactory.buildTree1();

		traverseRecursive(root);
		Util.print("--------------");
		traverseStack(root);
		Util.print("--------------");
		traverseStackLevelOrder(root);
		Util.print("--------------");
		System.out.println(count(root) + "  " + height(root));
	}

}
