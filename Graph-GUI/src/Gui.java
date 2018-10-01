import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.Dimension;


public class Gui extends JFrame {
	
	private JFrame mainFrame;
	private JButton showConnectedComponents, addAllEdges, showCutVertices, Help;
	protected GraphicalPicturePanel canvas;
	private JPanel controlPanel;
	protected JRadioButton addVertex, addEdge, removeVertex, removeEdge, moveVertex;
	protected VertexClickListener vertexListener;
	
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
		mainFrame.getContentPane().addMouseListener(new VertexClickListener());
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
		mainFrame.add(controlPanel);
	}
	
	public void setCanvas() {
		canvas = new GraphicalPicturePanel(this);
		canvas.setAlignmentX(RIGHT_ALIGNMENT);
		mainFrame.add(canvas);
	}
	
	public Boolean addVertex() {
		return addVertex.isSelected();
	}
	
	
}

