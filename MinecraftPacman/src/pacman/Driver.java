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

import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class Driver extends JPanel implements ActionListener,KeyListener,MouseListener{
	
	
	//handles drawing animation
	Timer animationTimer; //different
	
	int numImCoins = (int)(Math.random()*4) + 2;
	
	
	Background background;
	Background background2;
	Player player;
	
	int score = 0;
	
	Walls[] upperWalls = new Walls[12];
	Walls[] rightWalls = new Walls[14];
	Walls[] leftWalls = new Walls[14];
	Walls barrier1;
	Walls[] barrier2 = new Walls[5];
	Walls barrier3;
	Walls[] barrier4 = new Walls[3];
	Walls[] barrier5 = new Walls[3];
	Walls[] barrier6 = new Walls[3];
	Walls[] barrier7 = new Walls[2];
	Walls[] barrier8 = new Walls[2];
	Walls[] barrier9 = new Walls[3];
	Walls[] barrier10 = new Walls[3];
	Walls[] barrier11 = new Walls[3];
	Walls[] barrier12 = new Walls[3];
	Walls[] barrier13 = new Walls[3];
	Walls[] barrier14 = new Walls[3];
	Walls[] barrier15 = new Walls[2];
	RegCoin[][] coins = new RegCoin[13][11];
	Fruit fruit1;
	Fruit fruit2;
	ArrayList<ImmunityCoin> immuneCoins = new ArrayList<ImmunityCoin>();
	Ghost yellowGhost;
	Ghost blueGhost;
	Ghost pinkGhost;
	Ghost redGhost;
	
	Font big = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);
	Font biggest = new Font("Courier New", 1, 80);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//g.fillOval(0, 0, 200, 200);
		
		
		background2.paint(g);
		for(RegCoin[] temp: coins) {
			for(RegCoin temp1: temp) {
				temp1.paint(g);;
			}
		}
		player.paint(g);
		fruit1.paint(g);
		fruit2.paint(g);
		for(ImmunityCoin temp: immuneCoins) {
			temp.paint(g);
		}
		barrier1.paint(g);
		barrier3.paint(g);
	
		g.fillRect(250, 300, 150, 100);
		g.setColor(Color.PINK);
		g.fillRect(300, 290, 50, 20);
		yellowGhost.paint(g);
		blueGhost.paint(g);
		pinkGhost.paint(g);
		redGhost.paint(g);
		for(Walls temp: upperWalls) {
			temp.paint(g);
		}
		for(Walls temp: rightWalls) {
			temp.paint(g);
		}
		for(Walls temp: leftWalls) {
			temp.paint(g);
		}
		for(Walls temp: barrier2) {
			temp.paint(g);
		}
		for(Walls temp: barrier4) {
			temp.paint(g);
		}
		for(Walls temp: barrier5) {
			temp.paint(g);
		}
		for(Walls temp: barrier6) {
			temp.paint(g);
		}
		for(Walls temp: barrier7) {
			temp.paint(g);
		}
		for(Walls temp: barrier8) {
			temp.paint(g);
		}
		for(Walls temp: barrier9) {
			temp.paint(g);
		}
		for(Walls temp: barrier10) {
			temp.paint(g);
		}
		for(Walls temp: barrier11) {
			temp.paint(g);
		}
		for(Walls temp: barrier12) {
			temp.paint(g);
		}
		for(Walls temp: barrier13) {
			temp.paint(g);
		}
		for(Walls temp: barrier14) {
			temp.paint(g);
		}
		for(Walls temp: barrier15) {
			temp.paint(g);
		}
		
		g.setColor(Color.white);
		g.setFont(font2);

		g.drawString("Score: " + score, 20, 30);
		
		background.paint(g);
		
		for (int row = 0; row < coins.length; row++) {
			for (int col = 0; col < coins[0].length; col++) {
				if (coins[row][col].hitPlayer(player)) {
					coins[row][col].setX(1000);
					coins[row][col].setY(0);
					score += 10;
				}
			}
		}
		
		for (int i = 0; i < immuneCoins.size(); i++) {
			if (immuneCoins.get(i).hitPlayer(player)) {
				score += 40;
				immuneCoins.remove(i);
			}
		}
		
		if (fruit1.hitPlayer(player)) {
			score += 100;
			fruit1.setX(1000);
			fruit2.setY(0);
		}

		if (fruit2.hitPlayer(player)) {
			score += 100;
			fruit2.setX(1000);
			fruit2.setY(0);
		}
		
		
		if(yellowGhost.hitPlayer(player)) {
			player.reset();
		}
		
		if(pinkGhost.hitPlayer(player)) {
			player.reset();
		}
		
		if(blueGhost.hitPlayer(player)) {
			player.reset();
		}
		
		if(redGhost.hitPlayer(player)) {
			player.reset();
		}
		if ( player.getY() > 650) {
			player.setY(650);
		}
		if (player.getY() < 50) {
			player.setY(50);
		}
		if (player.getX() > 550) {
			player.setX(550);
		}
		if (player.getX() < 50) {
			player.setX(500);
		}
		
		
		/*int possVelocity = (int)(Math.random()*4) + 1;
		System.out.println(possVelocity);
		if(possVelocity == 1) {
			yellowGhost.setVx(1);
		}
		else if(possVelocity == 2) {
			yellowGhost.setVx(-1);
		}
		else if(possVelocity == 3) {
			yellowGhost.setVy(1);
		}
		else if(possVelocity == 4) {
			yellowGhost.setVy(-1);
		}
		if(yellowGhost.getVx() != 0) {
			yellowGhost.setVy(0);
		}
		else if(yellowGhost.getVy() != 0) {
			yellowGhost.setVx(0);
		}*/
		
		boolean teleported = false;
		
		if(yellowGhost.getX() >= 250 && yellowGhost.getX() <= 350 && yellowGhost.getY() >= 300 && yellowGhost.getY() <= 350) {
			yellowGhost.setVy(0);
			yellowGhost.setVx(0);
		}
		
		yellowGhost.move();
		
		if(score == 20 && !teleported) {
			yellowGhost.setX(300);
			yellowGhost.setY(250);
			yellowGhost.setVx(2);
			teleported = true;
		}
		
		for(Walls top: upperWalls) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(top.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls left: leftWalls) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(left.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls right: rightWalls) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(right.hitGhost(yellowGhost)) {
				yellowGhost.setX(550);
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		if(barrier1.hitGhost(yellowGhost)) {
			if(yellowGhost.getX() + 50 != barrier1.getX()) {
				yellowGhost.setVx(2);
				yellowGhost.setVy(0);
			}
			else if(yellowGhost.getX() - 50 != barrier1.getX()) {
				yellowGhost.setVx(-2);
				yellowGhost.setVy(0);
			}
			else if(yellowGhost.getY() + 50 != barrier1.getY()) {
				yellowGhost.setVx(0);
				yellowGhost.setVy(2);
			}
			else if(yellowGhost.getX() - 50 != barrier1.getY()) {
				yellowGhost.setVx(0);
				yellowGhost.setVy(-2);
			}
		}
		
		for(Walls two: barrier2) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(two.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		if(barrier3.hitGhost(yellowGhost)) {
			if(yellowGhost.getX() + 50 != barrier3.getX()) {
				yellowGhost.setVx(2);
				yellowGhost.setVy(0);
			}
			else if(yellowGhost.getX() - 50 != barrier3.getX()) {
				yellowGhost.setVx(-2);
				yellowGhost.setVy(0);
			}
			else if(yellowGhost.getY() + 50 != barrier3.getY()) {
				yellowGhost.setVx(0);
				yellowGhost.setVy(2);
			}
			else if(yellowGhost.getX() - 50 != barrier3.getY()) {
				yellowGhost.setVx(0);
				yellowGhost.setVy(-2);
			}
		}
		
		for(Walls four: barrier4) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(four.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls five: barrier5) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(five.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls six: barrier6) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(six.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls seven: barrier7) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(seven.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls eight: barrier8) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(eight.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls nine: barrier9) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(nine.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls ten: barrier10) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(ten.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls eleven: barrier11) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(eleven.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls twelve: barrier12) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(twelve.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls thirteen: barrier13) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(thirteen.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls fourteen: barrier14) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(fourteen.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		for(Walls fifteen: barrier15) {
			int possVelocity = (int)(Math.random()*4) + 1;
			if(fifteen.hitGhost(yellowGhost)) {
				if(possVelocity == 1) {
					yellowGhost.setVx(2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 2) {
					yellowGhost.setVx(-2);
					yellowGhost.setVy(0);
				}
				else if(possVelocity == 3) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(2);
				}
				else if(possVelocity == 4) {
					yellowGhost.setVx(0);
					yellowGhost.setVy(-2);
				}
			}
		}
		
		//g.fillOval(0, 0, 200, 200);
		
	}
	
	public Driver () {
		JFrame f = new JFrame("Example Drawing");
		f.setSize(650, 728);
		f.setResizable(false);
		f.addKeyListener(this);
		
		background = new Background("startScreen.png", 0,0);
		background2 = new Background("background.png",0,0);
		player = new Player();
		fruit1 = new Fruit();
		fruit2 = new Fruit(450, 550);
		/*immuneCoin1 = new ImmunityCoin(100, 50);
		immuneCoin2 = new ImmunityCoin(500, 250);
		immuneCoin3 = new ImmunityCoin(200, 500);*/
		yellowGhost = new Ghost("yellowminecraftghost.png", 250, 300);
		blueGhost = new Ghost("blueminecraftghost.png", 250, 350);
		pinkGhost = new Ghost("pinkminecraftghost.png", 350, 300);
		redGhost = new Ghost("redminecraftghost.png", 350, 350);
		barrier1 = new Walls(100, 100);
		barrier3 = new Walls(500,100);
		
		for(int i = 0; i < numImCoins; i++) {
			int x = 200;
			int y = 200;
			while((x == 200 && y == 200) || (x == 450 && y == 550) || (x > 250 && x < 400 && y > 300 && y < 400)) {
				x = (int)(Math.random()*12) + 1;
				y = (int)(Math.random()*13) + 1;
			}
			immuneCoins.add(new ImmunityCoin(x*50, y*50));
			
			//if(immuneCoins[i].getX())
		}
		
		for(int i = 0; i < upperWalls.length; i++) {
			upperWalls[i] = new Walls();
			upperWalls[i].setX(50*i);
		}
		
		for(int i = 0; i < rightWalls.length; i++) {
			rightWalls[i] = new Walls(600, 0);
			rightWalls[i].setY(50*i);
		}
		for(int i = 0; i < leftWalls.length; i++) {
			leftWalls[i] = new Walls();
			leftWalls[i].setY(50*i);
		}
		for(int i = 0; i < barrier2.length; i++) {
			barrier2[i] = new Walls(200,100);
			barrier2[i].setX(200 + 50*i);
		}
		for(int i = 0; i < barrier4.length; i++) {
			barrier4[i] = new Walls(50,200);
			barrier4[i].setX(50 + 50*i);
		}
		for(int i = 0; i < barrier5.length; i++) {
			barrier5[i] = new Walls(250,200);
			barrier5[i].setX(250 + 50*i);
		}
		for(int i = 0; i < barrier6.length; i++) {
			barrier6[i] = new Walls(450,200);
			barrier6[i].setX(450 + 50*i);
		}
		for(int i = 0; i < barrier7.length; i++) {
			barrier7[i] = new Walls(100,300);
			barrier7[i].setY(300 + 50*i);
		}
		for(int i = 0; i < barrier8.length; i++) {
			barrier8[i] = new Walls(500,300);
			barrier8[i].setY(300 + 50*i);
		}
		for(int i = 0; i < barrier9.length; i++) {
			barrier9[i] = new Walls(50,450);
			barrier9[i].setX(50 + 50*i);
		}
		for(int i = 0; i < barrier10.length; i++) {
			barrier10[i] = new Walls(50,500);
			barrier10[i].setX(50 + 50*i);
		}
		for(int i = 0; i < barrier11.length; i++) {
			barrier11[i] = new Walls(450,450);
			barrier11[i].setX(450 + 50*i);
		}
		for(int i = 0; i < barrier12.length; i++) {
			barrier12[i] = new Walls(450,500);
			barrier12[i].setX(450 + 50*i);
		}
		for(int i = 0; i < barrier13.length; i++) {
			barrier13[i] = new Walls(100,600);
			barrier13[i].setX(100 + 50*i);
		}
		for(int i = 0; i < barrier14.length; i++) {
			barrier14[i] = new Walls(400,600);
			barrier14[i].setX(400 + 50*i);
		}
		for(int i = 0; i < barrier15.length; i++) {
			barrier15[i] = new Walls(300,450);
			barrier15[i].setY(450 + 50*i);
		}
		/*for(int i = 0; i < coins.length; i++) {
			coins[i] = new RegCoin(67,67);
			coins[i].setY(67 + 50*i);
		}*/
		for(int row = 0; row < coins.length; row++) {
			for(int col = 0; col < coins[0].length; col++) {
				coins[row][col] = new RegCoin(67, 67);
				coins[row][col].setX(67 + 50*col);
				coins[row][col].setY(67 + 50*row);
			}
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
		// TODO Auto-generated method stub
		//System.out.println(e.getKeyCode());
		if(e.getKeyCode() == 38) {
			player.moveUp();
		} 
		else if (e.getKeyCode() == 40) {
			player.moveDown();
		} 
		else if (e.getKeyCode() == 37) {
			player.moveLeft();
		} 
		else if (e.getKeyCode() == 39) {
			player.moveRight();
		}
		if(e.getKeyCode() == 32) {
			background.hide();
			
			//setImg("background.png");
			
		}
		
		for(Walls two: barrier2) {
			if(two.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(two.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(two.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(two.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		for(Walls four: barrier4) {
			if(four.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(four.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(four.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(four.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		for(Walls five: barrier5) {
			if(five.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(five.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(five.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(five.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		for(Walls six: barrier6) {
			if(six.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(six.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(six.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(six.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		for(Walls seven: barrier7) {
			if(seven.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(seven.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(seven.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(seven.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		for(Walls eight: barrier8) {
			if(eight.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(eight.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(eight.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(eight.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		for(Walls nine: barrier9) {
			if(nine.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(nine.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(nine.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(nine.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		for(Walls ten: barrier10) {
			if(ten.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(ten.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(ten.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(ten.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		for(Walls eleven: barrier11) {
			if(eleven.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(eleven.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(eleven.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(eleven.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		for(Walls twelve: barrier12) {
			if(twelve.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(twelve.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(twelve.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(twelve.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		for(Walls thirteen: barrier13) {
			if(thirteen.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(thirteen.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(thirteen.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(thirteen.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		
			
		for(Walls fourteen: barrier14) {
			if(fourteen.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(fourteen.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(fourteen.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(fourteen.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		for(Walls fiveteen: barrier15) {
			if(fiveteen.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(fiveteen.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(fiveteen.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(fiveteen.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		}
		
		
			if(barrier1.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(barrier1.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(barrier1.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(barrier1.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
		
			if(barrier3.hitPlayer(player) && e.getKeyCode() == 39) {
				player.moveLeft();
			}
			if(barrier3.hitPlayer(player) && e.getKeyCode() == 40) {
				player.moveUp();
		}
			if(barrier3.hitPlayer(player) && e.getKeyCode() == 38) {
				player.moveDown();
			}
			if(barrier3.hitPlayer(player) && e.getKeyCode() == 37) {
				player.moveRight();
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}
