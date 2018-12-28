import java.util.HashMap;

public class Edge {	
	
	private Vertex first;
	private Vertex second;
	private Vertex[] neighbors;
	private int color;
	private float r,g,b = 0;
	
	public Edge(Vertex a, Vertex b) {
		first = a;
		second = b;
	}
	
	public void setNeighbors() {
		
	}
	
	public void clickedVert(Vertex x) {	}
	
	public Vertex getFirst() {return first;}
	
	public Vertex getSecond() {return second;}
	
	public void setColor(int x) {
		color = x;
	}
	
	public int getColor() { return color; }
	
	public void setR(float red) {r = red;}
	public void setG(float green) {g = green;}
	public void setB(float blue) {b = blue;}
	
	public float getR() {return r;}
	public float getG() {return g;}
	public float getB() { return b;}
	//End of Vertex class
}
