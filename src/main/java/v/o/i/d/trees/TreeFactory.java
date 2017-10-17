package v.o.i.d.trees;

import java.util.ArrayList;
import java.util.List;

public class TreeFactory {

	public static Node buildTree1() {
		Node a = new Node("A");
		Node c = new Node("C");
		Node b = new Node("B");
		b.left = a;
		b.right = c;
		Node d = new Node("D");
		d.left = b;

		Node g = new Node("G");
		Node f = new Node("F");
		f.right = g;
		Node h = new Node("H");
		h.left = f;

		Node e = new Node("E");
		e.left = d;
		e.right = h;
		return e;
	}
	
	public static List<GraphNode> buildGraph1() {
		List<GraphNode> graph = new ArrayList<>();
		
		GraphNode n0 = new GraphNode("0", 0);
		
		GraphNode n7 = new GraphNode("7", 7);
		n0.add(n7);
		
		GraphNode n1 = new GraphNode("1", 1);
		GraphNode n2 = new GraphNode("2", 2);
		GraphNode n4 = new GraphNode("4", 4);
		n7.add(n1);
		n7.add(n2);
		n7.add(n4);
		
		GraphNode n6 = new GraphNode("6", 6);
		GraphNode n5 = new GraphNode("5", 5);
		n4.add(n6);
		n4.add(n5);
		
		GraphNode n3 = new GraphNode("3", 3);
		n5.add(n3);
		
		graph.add(n0);
		graph.add(n1);
		graph.add(n2);
		graph.add(n3);
		graph.add(n4);
		graph.add(n5);
		graph.add(n6);
		graph.add(n7);
		
		return graph;
	}


	public static List<GraphNode> buildGraph2() {
		List<GraphNode> graph = new ArrayList<>();

		GraphNode n0 = new GraphNode("0", 0);
		GraphNode n1 = new GraphNode("1", 1);
		GraphNode n2 = new GraphNode("2", 2);
		GraphNode n3 = new GraphNode("3", 3);
		GraphNode n4 = new GraphNode("4", 4);
		GraphNode n5 = new GraphNode("5", 5);
		GraphNode n6 = new GraphNode("6", 6);
		GraphNode n7 = new GraphNode("7", 7);

		n0.add(n7);
		n0.add(n5);
		n0.add(n2);
		n0.add(n1);
		n0.add(n6);

		n1.add(n7);
		n1.add(n0);

		n2.add(n7);
		n2.add(n0);

		n3.add(n5);
		n3.add(n4);

		n4.add(n6);
		n4.add(n5);
		n4.add(n7);
		n4.add(n3);

		n5.add(n0);
		n5.add(n4);
		n5.add(n3);

		n6.add(n4);
		n6.add(n0);

		n7.add(n1);
		n7.add(n2);
		n7.add(n0);
		n7.add(n4);

		graph.add(n0);
		graph.add(n1);
		graph.add(n2);
		graph.add(n3);
		graph.add(n4);
		graph.add(n5);
		graph.add(n6);
		graph.add(n7);

		return graph;
	}


}
