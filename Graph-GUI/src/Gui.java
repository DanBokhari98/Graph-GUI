import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.Dimension;


public class Gui extends JFrame {
	
	private JButton showConnectedComponents, addAllEdges, showCutVertices, Help;
	protected GraphicalPicturePanel canvas;
	private JPanel controlPanel;
	protected JRadioButton addVertex, addEdge, removeVertex, removeEdge, moveVertex;
	RadioButtonListener rbl = new RadioButtonListener();
	ButtonListener btn = new ButtonListener();
	
	//Executes GUI
	public Gui(){
		super("Graph-GUI 2.0");
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

