import java.util.ArrayList;
import java.util.HashSet;

public class Graph {
		HashSet<Vertex> graph = new HashSet<>();
		private ArrayList<Vertex> vertices = new ArrayList<>();
		private ArrayList<Edge> edges = new ArrayList<>();
		
		public Graph() {
			
		}
		
		public ArrayList<Vertex> getVertices() {
			return vertices;
		}
		
		public ArrayList<Edge> getEdges() {
			return edges;
		}
		
		public void addVertex(int x, int y) {
			Vertex v = new Vertex(x,y);
			vertices.add(v);
		}
		
		public void addEdge(Vertex x, Vertex y) {
			Edge v = new Edge(x, y);
			edges.add(v);
		}
		
		public Vertex getVertex(int x, int y) {
			for(Vertex vertex : vertices) {
				if(x == vertex.getX() && y == vertex.getY()) {
					return vertex;
				}
			}
			return null;
		}
	//End of class
}
