import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;


public class Gui extends JFrame {
	
	private JFrame mainFrame;
	private JButton showConnectedComponents, addAllEdges, showCutVertices, Help;
	protected GraphicalPicturePanel canvas;
	private JPanel controlPanel;
	private JRadioButton addVertex, addEdge, removeVertex, removeEdge, moveVertex;
	
	public Gui(){
		super("Graph GUI");
		prepareGUI();
	}
	
	public void prepareGUI() {
		mainFrame = new JFrame("Graph GUI");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(900, 600);
		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.X_AXIS));
		mainFrame.setResizable(false);
		setLeftPanel();
		setCanvas();
		mainFrame.setVisible(true);
	}
	
	
	public void setButtons() {
		addAllEdges = new JButton("Add Edges");
		showConnectedComponents = new JButton("Connected Components");
		showCutVertices = new JButton("Show Cut Vertices");
		Help = new JButton("Help");
	}
	
	public void setRadioButtons() {
		addVertex = new JRadioButton("Add Vertex");
		addEdge = new JRadioButton("Add Edge");
		removeVertex = new JRadioButton("Remove Vertex");
		removeEdge = new JRadioButton("Remove Edge");
		moveVertex = new JRadioButton("Move Vertex");
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

	public void setLeftPanel() {
		setButtons();
		setRadioButtons();
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(9, 1));
		controlPanel.setAlignmentX(LEFT_ALIGNMENT);
		controlPanel.setPreferredSize(new Dimension(150, 600));
		controlPanel.add(addVertex);
		controlPanel.add(addEdge);
		controlPanel.add(removeVertex);
		controlPanel.add(removeEdge);
		controlPanel.add(moveVertex);
		controlPanel.add(addAllEdges);
		controlPanel.add(showConnectedComponents);
		controlPanel.add(showCutVertices);
		controlPanel.add(Help);
		mainFrame.add(controlPanel);
	}
	
	public void setCanvas() {
		canvas = new GraphicalPicturePanel(this);
		canvas.setAlignmentX(RIGHT_ALIGNMENT);
		mainFrame.add(canvas);
	}
	
}

