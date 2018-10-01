import java.util.HashMap;

public class Vertex {
	private double x, y;
	
	public Vertex(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
    public double distance(Vertex that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    public void point() {
    	
    }
}
