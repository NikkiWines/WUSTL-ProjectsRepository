package spath;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import heaps.Decreaser;
import heaps.MinHeap;
import spath.graphs.DirectedGraph;
import spath.graphs.Edge;
import spath.graphs.Vertex;
import timing.Ticker;
import spath.VertexAndDist;


// SHORTESTPATHS.JAVA
// Compute shortest paths in a graph.
//
// Your constructor should compute the actual shortest paths and
// maintain all the information needed to reconstruct them.  The
// returnPath() function should use this information to return the
// appropriate path of edge ID's from the start to the given end.
//
// Note that the start and end ID's should be mapped to vertices using
// the graph's get() function.
//
// You can ignore the input and startTime arguments to the constructor
// unless you are doing the extra credit.
//
public class ShortestPaths {
	private final static Integer inf = Integer.MAX_VALUE;
	private HashMap<Vertex, Decreaser<VertexAndDist>> map;
	private HashMap<Vertex, Edge> toEdge;
	private Map<Edge, Integer> weights;
	private Vertex startVertex;
	private final DirectedGraph g;
	// constructor
	public ShortestPaths(DirectedGraph g, Vertex startVertex, Map<Edge,Integer> weights) {
		this.map         = new HashMap<Vertex, Decreaser<VertexAndDist>>();
		this.toEdge      = new HashMap<Vertex, Edge>();
		this.weights     = weights;
		this.startVertex = startVertex;
		this.g           = g;
	}
	// this method does all the real work
	public void run() {
		Ticker ticker = new Ticker();
		MinHeap<VertexAndDist> pq = new MinHeap<VertexAndDist>(30000, ticker);

		// Initially all vertices are placed in the heap
		//   infinitely far away from the start vertex

		for (Vertex v : g.vertices()) {
			toEdge.put(v, null); // put in vertices, and edges == null
			VertexAndDist a = new VertexAndDist(v, inf); // set decreaser to inf (holds vertex and dist) 
			Decreaser<VertexAndDist> d = pq.insert(a); // insert into minheap
			map.put(v, d); // put into map 
		}
		// Now we decrease the start node's distance from itself to 0.
		// Note how we look up the decreaser using the map...
		Decreaser<VertexAndDist> startVertDist = map.get(startVertex);
		// and then decrease it using the Decreaser handle
		startVertDist.decrease(startVertDist.getValue().sameVertexNewDistance(0));
		// Extract nodes from the pq heap
		//   and act upon them as instructed in class and the text.
		while (pq.isEmpty() != true) {  // while the heap still has nodes
			// step 1: extract min
			VertexAndDist min = pq.extractMin();
			// step 2: update node:  --- whooooollleeee next part --- 
			Vertex minV = min.getVertex(); // grab node val
			int minD = min.getDistance(); // grab min's distance 
			ticker.tick(3);
			// initially this will be inf for all that aren't V1

			for (Edge edge: minV.edgesFrom()) { // for every edge from the extracted min vertex. // thank you to TA's for help understand this for loop!!
				Decreaser<VertexAndDist> dec = map.get(edge.to); //  decreaser object to hold vertexs and dists.
				int dist = weights.get(edge) + minD; // edge val plus old min dist == edge1weight - (inf - edge 2 weight) - (inf-edge 3 weight) - ....
				ticker.tick(2);
				if (dist <= dec.getValue().getDistance()) {  // if the distance is smaller than dec dist we need up call decreaser on map and toEdge...
					VertexAndDist updist = dec.getValue().sameVertexNewDistance(dist); // create new vertdist param to hold new information 
					dec.decrease(updist); // decrease dec
					toEdge.put(edge.to, edge); // update edges
					ticker.tick(4);
				}
				ticker.tick(); //for loop ticker
			}
			ticker.tick(); //  while loop
		}
	}
	/**
	 * Return a List of Edges forming a shortest path from the
	 *    startVertex to the specified endVertex.  Do this by tracing
	 *    backwards from the endVertex, using the map you maintain
	 *    during the shortest path algorithm that indicates which
	 *    Edge is used to reach a Vertex on a shortest path from the
	 *    startVertex.
	 * @param endVertex 
	 * @return
	 */
	public LinkedList<Edge> returnPath(Vertex endVertex) {
		LinkedList<Edge> path = new LinkedList<Edge>();
		while (endVertex != startVertex) { //  Do this by tracing backwards from the endVertex
			Edge edgev = toEdge.get(endVertex);// get end vertex edge from hashmap 
			// path.add == path.addLast 
			path.addFirst(edgev);// add to path in order from end to beginning....
			//grab the vertex from the edge that we added to the path 
			endVertex = edgev.from;
		}
		return path;
	}
	/**
	 * Return the length of all shortest paths.  This method
	 *    is completed for you, using your solution to returnPath.
	 * @param endVertex
	 * @return
	 */
	public int returnValue(Vertex endVertex) {
		LinkedList<Edge> path = returnPath(endVertex);
		int pathValue = 0;
		for(Edge e : path) {
			pathValue += weights.get(e);
		}
		return pathValue;
	}
}
