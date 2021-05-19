import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener{
	//handles drawing animation
	Timer animationTimer;
	Background startImg;
	
	
	Font big = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);
	Font biggest = new Font("Courier New", 1, 80);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		startImg.paint(g);
		g.setColor(Color.gray);
		g.fillRect(200, 270, 400, 50);
		g.setColor(Color.white);
		g.setFont(font2);
		g.drawString("Press Space to Start", 220, 300);
		
		
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
		f.addKeyListener(this);
		
		startImg = new Background("startScreen.png", 0, 0);
		
		//setup animation timer
		animationTimer = new Timer(16, this);
		animationTimer.start();
		
		f.setVisible(true);
	}

	public void update() {

	}
	
	//this method is invoked/called by the timer
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//call the frame to refresh
		update();
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		
		if(e.getKeyCode() == 32) {
			startImg.setImg("MinecraftImg.jpeg");
			
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
