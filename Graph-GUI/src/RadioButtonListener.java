import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RadioButtonListener extends MouseAdapter implements ActionListener {

	private Graph graph = GraphicalPicturePanel.getGraph();
	private String radioName;
	private static int id = 0;
	private static Vertex x;
	private static Vertex y;
	
	
	public RadioButtonListener() {
		super();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickCount = e.getClickCount();
		if (id == 1) {
			graph.addVertex(e.getX(), e.getY());
			Main.getGUI().getCanvas().repaint();
			System.out.println(e.getX() + " , " + e.getY());
		}
		// Not sure if this works
		//Check to see if add Edge radio button is on
		if(id == 2) {
			clickCount = 0;
			//If the user clicked once we get the first vertex
			if(clickCount == 1) {
				x = graph.getVertex(e.getX(), e.getY());
			}
			//If the user clicked twice we get the second vertex
			if(clickCount == 2) {
				y = graph.getVertex(e.getX(), e.getY());
			}
			graph.addEdge(x, y);
		}
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
	
		}
	}
	
	public static int getID() {
		return id;
	}

	public static Vertex getVertexOne() {
		return x;
	}
	
	public static Vertex getVertexTwo() {
		return y;
	}
	//End of class
}
