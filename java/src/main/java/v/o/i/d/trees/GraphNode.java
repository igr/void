package v.o.i.d.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph node.
 */
public class GraphNode {
	
	public int index;

	public GraphNode(Object item, int index) {
		this.adj = new ArrayList<>();
		this.item = item;
		this.index = index;
	}
	
	public List<GraphNode> adj;

	public final Object item;

	public void visit() {
		System.out.println("visit " + item.toString());
	}
	
	public void add(GraphNode node) {
		adj.add(node);
	}
}
