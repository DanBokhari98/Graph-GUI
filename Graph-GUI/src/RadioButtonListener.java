import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class RadioButtonListener extends MouseAdapter implements ActionListener {

	private Graph graph = GraphicalPicturePanel.getGraph();
	private String radioName;
	private static int id = 0;
	private static Vertex x;
	private static Vertex y;
	private static Vertex k;
	private static Vertex m;
	private static Vertex mvTemp;
	private static double e1, e2;
	private static int x1;
	private static int y1;

	public RadioButtonListener() {
		super();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		
		if (id == 1) {
			graph.addVertex(e.getX(), e.getY());
			Main.getGUI().getCanvas().repaint();
		}
		
		if(id == 2) {
			x1 = e.getX();
			y1 = e.getY();
				if(graph.isVertex(x1, y1) && m == null) {
					m = graph.getVertex(x1, y1);
					m.setColor(1);
				}
				Main.getGUI().getCanvas().repaint();
				if(graph.isVertex(e.getX(), e.getY()) && !m.equals(graph.getVertex(e.getX(), e.getY()))) {
					m.setColor(0);
					k = graph.getVertex(e.getX(), e.getY());
					graph.addEdge(m, k);
					Main.getGUI().getCanvas().repaint();
					m = null;
					k = null;
				}
				
		}
		
		if(id == 3) {
			if(graph.isVertex(e.getX(), e.getY())) {
				if(graph.isEdge(graph.getVertex(e.getX(), e.getY()))) {
					x1 = e.getX();
					y1 = e.getY();
					graph.removeEdge(graph.getVertex(e.getX(), e.getY()));
				}
				graph.removeVertex(graph.getVertex(e.getX(), e.getY()));
				Main.getGUI().getCanvas().repaint();
			}
		}
		if(id == 4) {
			e1 = e.getX();
			e2 = e.getY();
			removeEdge();
			Main.getGUI().getCanvas().repaint();
		}
		
		if(id == 5) {
			if(graph.isVertex(e.getX(), e.getY()) && mvTemp == null) {
				mvTemp = graph.getVertex(e.getX(), e.getY());
			}else if(!graph.isVertex(e.getX(), e.getY()) && mvTemp != null) {
				mvTemp.setX(e.getX());
				mvTemp.setY(e.getY());
				mvTemp = null;
				Main.getGUI().getCanvas().repaint();
			}
		}
		
	}
	
	public void removeEdge() {
		Edge ed = null;
		for(Edge e : graph.getEdges()) {
			if(Line2D.Double.ptLineDist(e.getFirst().getX(), e.getFirst().getY(), e.getSecond().getX(), e.getSecond().getY(), RadioButtonListener.getE1(), RadioButtonListener.getE2()) <= 10) {
				ed = e;
			}
		}
		if (ed != null) graph.getEdges().remove(ed);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		radioName = e.getActionCommand();
		if(radioName.equals("Add Vertex")) {
			id = 1;
		}
		if(radioName.equals("Add Edge")) {
			id = 2;
		}
		if(radioName.equals("Remove Vertex")) {
			id = 3;
		}
		if(radioName.equals("Remove Edge")) {
			id = 4;
		}
		if(radioName.equals("Move Vertex")) {
			id = 5;
		}
	}

	public static double getE1() { return e1; }
	public static double getE2() { return e2; }
	public  static int getX1() { return x1; }
	public static int getY1() { return y1; }
	
	public static int getID() {
		return id;
	}
	
	public static Vertex getVertexOne() {
		return x;
	}
	
	public static Vertex getVertexTwo() {
		return y;
	}
	//End of RadioButtonListener class
}
