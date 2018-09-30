import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;


public class Gui extends JFrame {
	
	private JFrame mainFrame;
	private JButton addVertex;
	private JButton addEdges;
	private JButton Help;
	private JPanel panel;
	private JLabel item1;
	private Panel controlPanel;
	
	
	
	public void prepareGUI() {
		mainFrame = new JFrame("Graph GUI");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(900, 600);
		mainFrame.setVisible(true);
//		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.PAGE_AXIS));
		mainFrame.setResizable(false);
		

		
		//Button Management
		addVertex.setSize(100, 200);
		addEdges.setSize(100, 200);
		addVertex = new JButton("Add vertex");
		addEdges = new JButton("Add Edge");
		addVertex.setBounds(0, 250, 120, 35);
		addEdges.setBounds(0, 250, 120, 35);


		//Panel settings
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(addVertex);
		panel.add(addEdges);
		
		//Appending button to JFrame
		mainFrame.add(panel);
		
	
	}
	
	
	public Gui(){
		super("Graph GUI");
		prepareGUI();
	}
	
	private void showCanvasDemo() {
		controlPanel.add(new MyCanvas());
		mainFrame.setVisible(true);
	}
	
	
	
	public class MyCanvas extends Canvas {
		
		public MyCanvas() {
			setBackground(Color.GRAY);
			setSize(300,300);
		}
	}

}

