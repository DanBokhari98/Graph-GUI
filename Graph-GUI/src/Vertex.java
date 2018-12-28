public class Vertex {
	private int x, y;
	private int color = 0;
	private boolean visited = false;
	
	public Vertex(int x, int y) {
		this.x = x;
		this.y = y;
		visited = false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setColor(int i) {
		this.color = i;
	}
	
	public int getColor() { return color; }
	
	public void visited() {
		visited = true;
	}
	
	public boolean isVisited() { return visited; }
	//End of Vertex class
}
