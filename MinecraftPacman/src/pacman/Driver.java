package pacman;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

//import frog.Squirrel;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class Driver extends JPanel implements ActionListener,KeyListener,MouseListener{
	
	
	//handles drawing animation
	Timer animationTimer; //different
	
	Background background;
	Player player;
	Walls[] upperWalls = new Walls[12];
	Walls[] rightWalls = new Walls[14];
	Walls[] leftWalls = new Walls[14];
	Coin coin;
	Fruit fruit;
	ImmunityCoin immuneCoin;
	Ghost ghost;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//g.fillOval(0, 0, 200, 200);
		
		background.paint(g);
		player.paint(g);
		coin.paint(g);
		fruit.paint(g);
		immuneCoin.paint(g);
		ghost.paint(g);
		
		for(Walls temp: upperWalls) {
			temp.paint(g);
		}
		for(Walls temp: rightWalls) {
			temp.paint(g);
		}
		for(Walls temp: leftWalls) {
			temp.paint(g);
		}
		
		
			if(ghost.hitPlayer(player)) {
				player.reset();
			}
		
		if ( player.getY() > 650) {
			player.setY(650);
		}
		if (player.getY() < 50) {
			player.setY(50);
		}
		if (player.getX() > 500) {
			player.setX(500);
		}
		if (player.getX() < 50) {
			player.setX(50);
		}
		
	}
	
	public Driver () {
		JFrame f = new JFrame("Example Drawing");
		f.setSize(600, 728);
		f.setResizable(false);
		f.addKeyListener(this);
		
		background = new Background();
		player = new Player();
		coin = new Coin();
		fruit = new Fruit();
		immuneCoin = new ImmunityCoin();
		ghost = new Ghost();
		
		for(int i = 0; i < upperWalls.length; i++) {
			upperWalls[i] = new Walls();
			upperWalls[i].setX(50*i);
		}
		
		for(int i = 0; i < rightWalls.length; i++) {
			rightWalls[i] = new Walls(550, 0);
			rightWalls[i].setY(50*i);
		}
		for(int i = 0; i < leftWalls.length; i++) {
			leftWalls[i] = new Walls();
			leftWalls[i].setY(50*i);
		}
	
		//set default action for x button
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//add this panel to the JFrame
		//allows connection with "drawing"
		f.add(this);
		//setup animation timer
		animationTimer = new Timer(16, this);
		animationTimer.start();
		f.addMouseListener(this);
		
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//froggy.methodHop();
		System.out.println(e.getKeyCode());
		if(e.getKeyCode() == 38) {
			player.moveUp();
		} else if (e.getKeyCode() == 40) {
			player.moveDown();
		} else if (e.getKeyCode() == 37) {
			player.moveLeft();
		} else if (e.getKeyCode() == 39) {
			player.moveRight();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}