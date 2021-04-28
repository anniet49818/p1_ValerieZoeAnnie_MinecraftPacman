import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener, KeyListener{
	//handles drawing animation
	Timer animationTimer;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//g.fillOval(0, 0, 200, 200);
	}
	
	public Driver () {
		JFrame f = new JFrame("Minecraft Pacman");
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}
