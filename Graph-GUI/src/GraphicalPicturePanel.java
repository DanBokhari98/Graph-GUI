import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class GraphicalPicturePanel extends JPanel{
	
	static Graph graph = new Graph();
	static Ellipse2D highlighted;
	public static Graphics2D g2;
	private Random rand = new Random();
	float r, gg, b;
	public GraphicalPicturePanel() {
		
	}
	
	public static Graph getGraph() {
		return graph;
	}
	
	@Override
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.setPaint(Color.BLUE);
		g2.setStroke(new BasicStroke(1.0f));
		if(graph.getVertices().size() != 0) {
			for(Vertex v : graph.getVertices()) {
				if(v.getColor() == 1) {
					g2.setColor(Color.GREEN);
					Ellipse2D.Double circle = new Ellipse2D.Double(v.getX(), v.getY(), 12, 12);
					g2.fill(circle);
				}else if(v.getColor() == 1000) {
					g2.setColor(Color.ORANGE);
					Ellipse2D.Double circle = new Ellipse2D.Double(v.getX(), v.getY(), 13, 10);
					highlighted = circle;
					g2.fill(circle);
				} else {
					g2.setColor(Color.BLUE);
					Ellipse2D.Double circle = new Ellipse2D.Double(v.getX(), v.getY(), 10, 10);
					highlighted = circle;
					g2.fill(circle);
				}
			}
			
		}
		
		if(graph.getEdges().size() != 0) {
			g2.setStroke(new BasicStroke(4.5f));
			g2.setPaint(Color.BLUE);
			setColorEdges();
			for(Edge e :  graph.getEdges()) {
				if(e.getColor() == 0){
					g2.setPaint(Color.BLUE);
					int x1 = e.getFirst().getX() + 5;
					int y1 = e.getFirst().getY() + 5;
					int x2 = e.getSecond().getX() + 5;
					int y2 = e.getSecond().getY() + 5;
					g2.drawLine(x1, y1, x2, y2);
				}
				if(e.getColor() >= 2 ) {
					if(e.getR() == 0 && e.getB() == 0 && e.getG() == 0) {
						g2.setPaint(Color.GREEN);
						int x1 = e.getFirst().getX() + 5;
						int y1 = e.getFirst().getY() + 5;
						int x2 = e.getSecond().getX() + 5;
						int y2 = e.getSecond().getY() + 5;
						g2.drawLine(x1, y1, x2, y2);
					}else {
						g2.setPaint(new Color(e.getR(), e.getG(), e.getB()));
						int x1 = e.getFirst().getX() + 5;
						int y1 = e.getFirst().getY() + 5;
						int x2 = e.getSecond().getX() + 5;
						int y2 = e.getSecond().getY() + 5;
						g2.drawLine(x1, y1, x2, y2);
					}
				}		
			}
		}
	//End of paintComponent
	}
	
	public void setColorEdges() {
		Edge it = null;
		float r,gg,b =0;
		r = rand.nextFloat();
		gg = rand.nextFloat();
		b = rand.nextFloat();
		Iterator<Edge> i = graph.getEdges().iterator();
		it = i.next();
		for(Edge e : graph.getEdges()) {
			if(e.getFirst().getColor() == e.getSecond().getColor()) {
				e.setColor(e.getFirst().getColor());
			}
			System.out.println(e.getColor());
		}
		for(Edge e : graph.getEdges()) {
			
			if(e.getColor() == it.getColor()) {
				e.setR(r);
				e.setG(gg);
				e.setB(b);
			}
			  		
			while(i.hasNext() && e.getColor() == it.getColor()) {
				e.setR(r);
				e.setG(gg);
				e.setB(b);
				it = i.next();
				//Check very last one

				if(e.getColor() < it.getColor()) {
					r = rand.nextFloat();
					gg = rand.nextFloat();
					b = rand.nextFloat();
				}
			}
		}
		
		
	}
	//End of GraphicalPicturePanel class
}
