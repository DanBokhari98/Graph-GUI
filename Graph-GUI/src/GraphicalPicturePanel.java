import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class GraphicalPicturePanel extends JPanel{

	static Graph graph = new Graph();
	
	public GraphicalPicturePanel() {
		
	}
	
	public static Graph getGraph() {
		return graph;
	}
	
	@Override
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.BLUE);
		g2.setStroke(new BasicStroke(1.0f));
		if(graph.getVertices().size() != 0) {
			for(Vertex v: graph.getVertices()) {
				Ellipse2D.Double circle = new Ellipse2D.Double(v.getX(), v.getY(), 10, 10);
				g2.fill(circle);
			}
			
		}
		
		//Code doesn't work
		if(graph.getEdges().size() != 0) {
			g2.setPaint(Color.RED);
			for(Edge e : graph.getEdges()) {
				// e.getX() is Vertex X
				int x1 = e.getX().getX();
				int y1 = e.getX().getY();
				// e.getY() is Vertex Y
				int x2 = e.getY().getX();
				int y2 = e.getY().getY();
				g2.drawLine(x1, y1, x2, y2);
			}
		}
	}
	
	//End of class
}
