import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VertexClickListener extends MouseAdapter{
	
	private int x, y;

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
		System.out.println(x + " , " + y);
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
