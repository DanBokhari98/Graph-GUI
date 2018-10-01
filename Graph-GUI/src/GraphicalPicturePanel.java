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


public class GraphicalPicturePanel extends JPanel implements ActionListener{
	
	Gui frame;
	Canvas canvas;
	JPanel canvasWindow;
	private int x, y;
	
	public GraphicalPicturePanel(Gui frame) {
		super();
		this.frame = frame;
//		frame.getContentPane().addMouseListener(new VertexClickListener());
		canvasWindow = new JPanel();
		
	}
	
	public void setCanvas() {
		
		canvasWindow.add(canvas);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.BLUE);
		g2.setStroke(new BasicStroke(1.0f));
//		Ellipse2D.Double circle = new Ellipse2D.Double(200, 500, 10, 10);
//		g2.fill(circle);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
