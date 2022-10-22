package v.o.i.d.tree;

public class TreeWalker {

	static TreeNode root;

	public static void main(String[] args) {
		root = new TreeNode("root",
			new TreeNode("T1"),
			new TreeNode("T2", new TreeNode("L4"), new TreeNode("L5")),
			new TreeNode("T3")
		);
		walkNode(root);
	}

	private static void walkNode(TreeNode node) {
		System.out.println(node.toString());
		node.forEachChild(TreeWalker::walkNode);
	}

}
