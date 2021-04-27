import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener{
	//handles drawing animation
	Timer animationTimer;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//g.fillOval(0, 0, 200, 200);
	}
	
	public Driver () {
		JFrame f = new JFrame("Example Drawing");
		f.setSize(800, 600);
		
		//set default action for x button
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add this panel to the JFrame
		//allows connection with "drawing"
		f.add(this);
		
		
		//setup animation timer
		animationTimer = new Timer(16, this);
		animationTimer.start();
		
		f.setVisible(true);
	}

	
	//this method is invoked/called by the timer
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//call the frame to refresh
		repaint();
	}



}
