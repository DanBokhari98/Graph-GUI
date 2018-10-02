import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ButtonListener implements ActionListener{
	private String buttonName;
	private JFrame help;
	private int id = 0;
	
	public ButtonListener() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonName = e.getActionCommand();
		if(buttonName.equals("Show Cut Vertices")) {
			id = 1;
		}
		if(buttonName.equals("Add Edges")) {
			id = 2;
		}
		if(buttonName.equals("Connected Components")) {
			id = 3;
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
				+ "\n\n To add a vertex click the radio button and click on"
				+ "\n the right side of the screen (The canvas) to place a dot."
				+ "\n--------------------------------------------------------------------------------------"
				+ "\n            Add an edge by clicking on two vertices   "
				+ "\n    If you want to add all the edges, click add all edges"
				+ "\n--------------------------------------------------------------------------------------");
			help.add(helpMenu);
			help.setVisible(true);	
		}

	//End of class
}
