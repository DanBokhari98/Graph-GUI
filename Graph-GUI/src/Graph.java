import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Graph {
		HashSet<Vertex> graph = new HashSet<>();
		private ArrayList<Vertex> vertices = new ArrayList<>();
		private ArrayList<Edge> edges = new ArrayList<>();
		private ArrayList<Vertex> adj[];
		private HashSet<Vertex> dfs = new HashSet<>();
		private static int color = 2;
		private Random rand = new Random();
		
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
			Edge e  = new Edge(x, y);
			edges.add(e);
		}
		
		public Vertex getVertex(int xc, int yc) {
			boolean found = false;
			for(Vertex c : vertices) {
				int dx = c.getX() - xc;
			    int dy = c.getY() - yc;
			    int r = 10;
			    found = dx*dx+dy*dy <= r*r;
			    if(found) {
			    	return c;
			    }
			}
		    return null;
		}
		
		public ArrayList<Vertex> getVerticesList() {
			return vertices;
		}
		
		public void removeVertex(Vertex x) {
			vertices.remove(x);
		}
		
		public void setAllEdges() {		
			for(Vertex x : vertices) {
				int k = vertices.indexOf(x) + 1;
				while(k < vertices.size()) {
					if(isEdge(x, vertices.get(k))) {
						k++;
						continue;
					}
					addEdge(x, vertices.get(k++));
				}
			}
		}
		
		public void removeEdge(Vertex x) {
			while(isEdge(x)) {
				Edge temp = getEdge(x);
				edges.remove(temp);
			}	
		}
		
		public Edge getEdge(Vertex x) {
			Edge d = null;
			if(isEdge(x)) {
				for(Edge e : edges) {
					if(e.getFirst() == x) d = e; 
					else if(e.getSecond() == x) d = e;
				}
			}else {
				return null;
			}
			return d;
		}
		
		public boolean isEdge(Vertex k) {
			boolean found = false;
			for(Edge e : edges) {
				if(k == e.getFirst() || k == e.getSecond()) {
					found = true;
					break;
				}
			}
			return found;
		}
		
		public boolean isVertex(int xc, int yc) {
			boolean found = false;
			for(Vertex c : vertices) {
				int dx = c.getX() - xc;
			    int dy = c.getY() - yc;
			    int r = 10;
			    found = dx*dx+dy*dy <= r*r;
			    if(found) break;
			}
		    return found;
		}
		
		public boolean isEdge(Vertex x, Vertex z) {
			for(Edge e : edges) {
				if(e.getFirst() == x && e.getSecond() == z || e.getFirst() == z && e.getSecond() == x) {
					return true;
				}
			}
			return false;
			
		}
		
		public void createAdjList() {
			adj = new ArrayList[vertices.size()];
			for(int i = 0; i < vertices.size(); i++) {
				adj[i] = new ArrayList<>();
			}	
		}
		

		public void addAdjEdges() {
			for(Edge e : edges) {
				adj[vertices.indexOf(e.getFirst())].add(e.getSecond());
				adj[vertices.indexOf(e.getSecond())].add(e.getFirst());
			}
		}

		public void DFSUtil(Vertex x, boolean visited[], int color) {
			visited[vertices.indexOf(x)] = true;
			Iterator<Vertex> i = adj[vertices.indexOf(x)].listIterator();
			Vertex k = null;
			while(i.hasNext()) {
				k = (Vertex) i.next();
				k.setColor(color);
				k.visited();
				if(!visited[vertices.indexOf(k)]) { 	
					DFSUtil(k, visited, color);
				}
			}
		}
		
		//Auxiliary method
		public void DFS(Vertex x) {
			createAdjList();
			addAdjEdges();
			boolean visited[] = new boolean[vertices.size()];
			for(int i = 0; i< visited.length; i++) {
				visited[i] = false;
			}
			for(Vertex v: vertices) {
				if (!visited[vertices.indexOf(v)]) {
					DFSUtil(v, visited, color++);
				}
			}
		}

		public ArrayList<Vertex>[] getAdj() { return adj; }
		
		public int getColor() { return color; }
		public void setColor() { color += 1;} 
		
	//End of Graph class
}
