import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GG0464 {
	private static Gui gui;
	
	public GG0464() {
		getGUI();
	}
	
	public static void main(String [] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {}
		gui = new Gui();
	}
	public static Gui getGUI() {
		return gui;
	}
	//End of class
}

class Gui extends JFrame {
	private JButton showConnectedComponents, addAllEdges, showCutVertices, Help;
	protected GraphicalPicturePanel canvas;
	private JPanel controlPanel;
	protected JRadioButton addVertex, addEdge, removeVertex, removeEdge, moveVertex;
	RadioButtonListener rbl = new RadioButtonListener();
	ButtonListener btn = new ButtonListener();
	
	//Executes GUI
	public Gui(){
		super("Graph GUI");
		prepareGUI();
	}
	
	//Sets up GUI
	public void prepareGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setLocationRelativeTo(null);
		setResizable(false);
		setLeftPanel();
		setCanvas();
		setVisible(true);
	}
	
	
	public void setButtons() {
		addAllEdges = new JButton("Add Edges");
		showConnectedComponents = new JButton("Connected Components");
		showCutVertices = new JButton("Show Cut Vertices");
		Help = new JButton("Help");
		addAllEdges.addActionListener(btn);
		showConnectedComponents.addActionListener(btn);
		showCutVertices.addActionListener(btn);
		Help.addActionListener(btn);
	}
	
	public void setRadioButtons() {
		addVertex = new JRadioButton("Add Vertex");
		addEdge = new JRadioButton("Add Edge");
		removeVertex = new JRadioButton("Remove Vertex");
		removeEdge = new JRadioButton("Remove Edge");
		moveVertex = new JRadioButton("Move Vertex");
		
		
		//Set rbl to radiobuttons
		addVertex.addActionListener(rbl);
		addEdge.addActionListener(rbl);
		removeVertex.addActionListener(rbl);
		removeEdge.addActionListener(rbl);
		moveVertex.addActionListener(rbl);
		
		setButtonGroup();
	}
	
	public void setButtonGroup() {
		ButtonGroup group = new ButtonGroup();
		group.add(addVertex);
		group.add(addEdge);
		group.add(removeEdge);
		group.add(removeVertex);
		group.add(moveVertex);
	}

	//Initialize buttons, groups and Control JPanel
	public void setLeftPanel() {
		setButtons();
		setRadioButtons();
		controlPanel = new JPanel();
		
		controlPanel.setLayout(new GridLayout(9, 1));
		controlPanel.setAlignmentX(LEFT_ALIGNMENT);
		controlPanel.setPreferredSize(new Dimension (250, 600));
		controlPanel.setMaximumSize(new Dimension(250, 600));
		controlPanel.setMinimumSize(new Dimension(250, 600));
		controlPanel.add(addVertex);
		controlPanel.add(addEdge);
		controlPanel.add(removeVertex);
		controlPanel.add(removeEdge);
		controlPanel.add(moveVertex);
		controlPanel.add(addAllEdges);
		controlPanel.add(showConnectedComponents);
		controlPanel.add(showCutVertices);
		controlPanel.add(Help);
		add(controlPanel);
	}
	
	//Creating Canvas JPanel
	public void setCanvas() {
		canvas = new GraphicalPicturePanel();
		canvas.setAlignmentX(RIGHT_ALIGNMENT);
		canvas.addMouseListener(rbl);
		add(canvas);
	}
	
	//Was going to use this to check if radio button is on add vertex.
	public Boolean addVertex() {
		return addVertex.isSelected();
	}
	
	public GraphicalPicturePanel getCanvas() {
		return canvas;
	}
	
	//Get Action Listener
	
//End of Gui class	
}



class GraphicalPicturePanel extends JPanel{
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
				} else {
					g2.setColor(Color.BLUE);
					Ellipse2D.Double circle = new Ellipse2D.Double(v.getX(), v.getY(), 10, 10);
					highlighted = circle;
					g2.fill(circle);
				}
				if(v.getShowCut() == 1000) {
					g2.setColor(Color.PINK);
					Ellipse2D.Double circle = new Ellipse2D.Double(v.getX(), v.getY(), 13, 13);
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
				}else if(e.getColor() >= 2){
						g2.setPaint(new Color(e.getR(), e.getG(), e.getB()));
						int x1 = e.getFirst().getX() + 5;
						int y1 = e.getFirst().getY() + 5;
						int x2 = e.getSecond().getX() + 5;
						int y2 = e.getSecond().getY() + 5;
						g2.drawLine(x1, y1, x2, y2);
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
		ArrayList<Integer> p = new ArrayList<>();
		for(Edge e : graph.getEdges()) {
			if(e.getFirst().getColor() == e.getSecond().getColor()) {
				e.setColor(e.getFirst().getColor());
				p.add(e.getColor());
			}
		}
		
		
		//What if I check the vertex? 
		for(Vertex m : graph.getVertices()) {
			
			for(Edge e: graph.getEdges()) {
				if(e.getColor() == m.getColor()) {
					e.setR(r);
					e.setG(gg);
					e.setB(b);
				}else {
					r = rand.nextFloat();
					gg = rand.nextFloat();
					b = rand.nextFloat();
				}
			}
		}
	}
	//End of GraphicalPicturePanel class
}

class Graph {
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
	public void APUtil(Vertex u, boolean visited[], int disc[], int low[], int parent[], boolean ap[]){ 
		int children = 0; 
		visited[vertices.indexOf(u)] = true; 
		disc[vertices.indexOf(u)] = low[vertices.indexOf(u)] = ++time; 
		Iterator<Vertex> i = adj[vertices.indexOf(u)].iterator(); 
		while (i.hasNext()) { 
        Vertex v = i.next(); 
        if (!visited[vertices.indexOf(v)]) { 
            children++; 
            parent[vertices.indexOf(v)] = vertices.indexOf(u); 
            APUtil(v, visited, disc, low, parent, ap); 
            low[vertices.indexOf(u)]  = Math.min(low[vertices.indexOf(u)], low[vertices.indexOf(u)]); 

            if (parent[vertices.indexOf(u)] == NIL && children > 1) 
                ap[vertices.indexOf(u)] = true; 

            if (parent[vertices.indexOf(u)] != NIL && low[vertices.indexOf(v)] >= disc[vertices.indexOf(u)]) 
                ap[vertices.indexOf(u)] = true; 
        }else if (vertices.indexOf(v) != parent[vertices.indexOf(u)]) 
            low[vertices.indexOf(u)]  = Math.min(low[vertices.indexOf(u)], disc[vertices.indexOf(v)]); 
    } 
} 	
	
	
	
	public void AP(){
		createAdjList();
		addAdjEdges();
		boolean visited[] = new boolean[vertices.size()]; 
		int disc[] = new int[vertices.size()]; 
		int low[] = new int[vertices.size()]; 
		int parent[] = new int[vertices.size()]; 
		boolean ap[] = new boolean[vertices.size()]; 	  
		for (int i = 0; i < vertices.size(); i++) { 
	            parent[i] = NIL; 
	            visited[i] = false; 
	            ap[i] = false; 
	        } 
		for (Vertex v : vertices) 
			if (visited[vertices.indexOf(v)] == false) 
				APUtil(v, visited, disc, low, parent, ap); 

	        for (Vertex v : vertices) { 
	            if (ap[vertices.indexOf(v)] == true) 
	               v.setShowCut(1000);
	        }
	    } 
	
	
	int time = 0; 
	static final int NIL = -1; 
	public ArrayList<Vertex>[] getAdj() { return adj; }
	
	public int getColor() { return color; }
	public void setColor() { color += 1;} 
	
//End of Graph class
}


class RadioButtonListener extends MouseAdapter implements ActionListener {
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
			GG0464.getGUI().getCanvas().repaint();
		}
		
		if(id == 2) {
			x1 = e.getX();
			y1 = e.getY();
				if(graph.isVertex(x1, y1) && m == null) {
					m = graph.getVertex(x1, y1);
					m.setColor(1);
				}
				GG0464.getGUI().getCanvas().repaint();
				if(graph.isVertex(e.getX(), e.getY()) && !m.equals(graph.getVertex(e.getX(), e.getY()))) {
					m.setColor(0);
					k = graph.getVertex(e.getX(), e.getY());
					graph.addEdge(m, k);
					GG0464.getGUI().getCanvas().repaint();
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
				GG0464.getGUI().getCanvas().repaint();
			}
		}
		if(id == 4) {
			e1 = e.getX();
			e2 = e.getY();
			removeEdge();
			GG0464.getGUI().getCanvas().repaint();
		}
		
		if(id == 5) {
			if(graph.isVertex(e.getX(), e.getY()) && mvTemp == null) {
				mvTemp = graph.getVertex(e.getX(), e.getY());
				mvTemp.setColor(1);
			}
			GG0464.getGUI().getCanvas().repaint();
			if(!graph.isVertex(e.getX(), e.getY()) && mvTemp != null) {
				mvTemp.setX(e.getX());
				mvTemp.setY(e.getY());
				mvTemp.setColor(0);
				mvTemp = null;
				GG0464.getGUI().getCanvas().repaint();
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


class ButtonListener implements ActionListener{
	private String buttonName;
	private JFrame help;
	private static int id = 0;
	static Graph graph = GraphicalPicturePanel.getGraph();
	public ButtonListener() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonName = e.getActionCommand();
		
		if(buttonName.equals("Add Edges")) {
			id = 1;
			graph.setAllEdges();
			GG0464.getGUI().getCanvas().repaint();
			id = 0;
		}
		if(buttonName.equals("Connected Components")) {
			id = 2;
			graph.DFS(graph.getVertices().get(0));
			GG0464.getGUI().getCanvas().repaint();
		}
		if(buttonName.equals("Show Cut Vertices")) {
			id = 3;
			graph.AP();
			GG0464.getGUI().getCanvas().repaint();
		}
		if(buttonName.equals("Help")) {
			id = 4;
			help = new JFrame("Help");
			setHelpFrame();
		}
	}
	
	
	public void setHelpFrame() {
			help.setSize(600, 600);
			help.setBackground(Color.YELLOW);
			help.setResizable(false);
			JTextArea helpMenu = new JTextArea(); 
			helpMenu.setBackground(Color.YELLOW);
			helpMenu.setFont(helpMenu.getFont().deriveFont(22f));
			helpMenu.setEditable(false);
			helpMenu.setText("--------------------------------------------------------------------------------------"
				+ "\n\tWelcome to the Graph-Gui"
				+ "\n\n         To add a vertex click the radio button and click on"
				+ "\n the right side of the screen (The canvas) to place a dot."
				+ "\n--------------------------------------------------------------------------------------"
				+ "\n            Add an edge by clicking on two vertices   "
				+ "\n    If you want to add an edge between all vertices \n\t   click add all edges"
				+ "\n--------------------------------------------------------------------------------------"
				+ "\n            Move a vertex by clicking on a vertex once  "
				+ "\n    and click on another part of the canvas to move it"
				+ "\n--------------------------------------------------------------------------------------");
			help.add(helpMenu);
			help.setVisible(true);	
		}
	
	public static int idOfButton() {
		return id;
	}
	
	public static void setIdButton(int newID) {
		id = newID;
	}
	
	//End of ButtonListener class
}


class Edge {	
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


class Vertex {
private int x, y;
private int color = 0;
private boolean visited = false;
private int showCut = 0;

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

public void setShowCut(int i) {
	showCut = i;
}

public int getShowCut() { return showCut; }


public boolean isVisited() { return visited; }
//End of Vertex class
}

