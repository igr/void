package v.o.i.d.trees;

public class Node {

	public Node(Object item) {
		this.item = item;
	}

	public Node left;
	public Node right;
	public final Object item;

	public void visit() {
		System.out.println("visit " + item.toString());
	}
}
