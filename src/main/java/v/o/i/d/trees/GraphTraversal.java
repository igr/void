package v.o.i.d.trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphTraversal {
	
	List<GraphNode> graph;
	boolean[] visited;

	public GraphTraversal(List<GraphNode> graph) {
		this.graph = graph;
		this.visited = new boolean[graph.size()];
	}
	
	public void dfs() {
		dfs(0);
	}
	
	/**
	 * To visit all the nodes connected to node k in a graph, we mark it as visited, then
	 * (recursively) visit all the unvisited nodes on k's adjacency list.
	 * 
	 * The difference between depth-first search and general tree traversal is
	 * that we need to guard explicitly against visiting nodes that we have already
	 * visited. In a tree, we never encounter any such nodes. Indeed, if the graph
	 * is a tree, recursive depth-first search starting at the root is
	 * equivalent to preorder traversal.
	 * 
	 * Depth-first search requires time proportional to V + E in a graph with
	 * V vertices and E edges, using the adjacency lists representation.
	 * 
	 */
	private void dfs(int k) {
		GraphNode knode = graph.get(k);
		knode.visit();
		visited[k] = true;
		
		for (GraphNode t : knode.adj) {
			if (visited[t.index] == false) {
				dfs(t.index);
			}
		}
	}
	
	
	public void bfs() {
		bfs(0);
	}
	

	/**
	 * To visit all the nodes connected to node k in a graph, we put k onto a FIFO queue,
	 * then enter into a loop where we get the next node from the queue, and,
	 * if it has not been visited, visit it and push all the unvisited nodes on
	 * its adjacency list, continuing until the queue is empty.
	 */
	void bfs(int k) {
		Queue<Integer> q = new LinkedList<>();
		q.add(k);
		while (q.isEmpty() == false) {
			k = q.remove();
			if (visited[k] == false) {
				GraphNode knode = graph.get(k);
				knode.visit();
				visited[k] = true;
				for (GraphNode t : knode.adj) {
					if (visited[t.index] == false) {
						q.add(t.index);
					}
				}
			}
		}
	}

	
	public static void main(String[] args) {
		List<GraphNode> graph = TreeFactory.buildGraph2();
		GraphTraversal gt = new GraphTraversal(graph);
		gt.dfs();
		System.out.println("----------------");
		gt = new GraphTraversal(graph);
		gt.bfs();
	}

}
