package v.o.i.d.tree;

import java.util.Arrays;
import java.util.function.Consumer;

public class TreeNode {

	private final TreeNode[] children;
	private final String name;

	public TreeNode(String name, TreeNode... children) {
		this.name = name;
		this.children = children;
	}

	public TreeNode forEachChild(Consumer<TreeNode> treeNodeConsumer) {
		Arrays.stream(children).forEach(treeNodeConsumer);
		return this;
	}

	@Override
	public String toString() {
		return "TreeNode{" +
			"name='" + name + '\'' +
			'}';
	}
}
