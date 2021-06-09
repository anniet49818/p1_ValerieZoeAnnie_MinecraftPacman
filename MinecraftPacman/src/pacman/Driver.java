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
	Music soundBackground = new Music("Map.wav", true);
	Music deadSound = new Music("dead.wav", false);
	Music deadSound2 = new Music("dead.wav", false);
	Music deadSound3 = new Music("dead.wav", false);
	Music deadSound4 = new Music("dead.wav", false);
	Music coinSound = new Music("coin.wav", false);
	Music immuneSound = new Music("immune.wav", false);
	Music fruitSound = new Music("fruit.wav", false);
	Music fruitSound2 = new Music("fruit.wav", false);
	int numImCoins = (int)(Math.random()*4) + 3;
	boolean isImmune = false;
	boolean stopImmune = false;
	long beginningImmune = 0;
	long endImmune = 0;
	
	Background background;
	Background background2;
	Player player;
	
	int score = 0;
	int lives = 3;
	int count = 0;
	int num = 0;
	int num2 = 0;
	int num3 = 0;
	int num4 = 0;
	
	ArrayList<Walls[]> wallNames = new ArrayList<Walls[]>();
	
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
	Walls[] barrier16 = new Walls[3];
	Walls[] barrier17 = new Walls[3];
	Walls[] bottomWalls= new Walls[12];
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
	
		yellowGhost.paint(g);
		blueGhost.paint(g);
		pinkGhost.paint(g);
		redGhost.paint(g);
		for(int i = 0; i < wallNames.size(); i++) {
			for(Walls temp: wallNames.get(i)) {
				temp.paint(g);
			}
		}
		
		g.setColor(Color.white);
		g.setFont(font2);

		g.drawString("Score: " + score, 20, 30);
		g.drawString("Lives: " + lives, 300, 30);
		
		background.paint(g);
		
		for (int row = 0; row < coins.length; row++) {
			for (int col = 0; col < coins[0].length; col++) {
				if (coins[row][col].hitPlayer(player)) {
					coins[row][col].setX(1000);
					coins[row][col].setY(0);
					score += 10;
					count++;
					coinSound.play();
				}
			}
		}
		
		
		for (int i = 0; i < immuneCoins.size(); i++) {
			if (immuneCoins.get(i).hitPlayer(player)) {
				immuneSound.play();
				beginningImmune = System.currentTimeMillis();
				System.out.println("hit");
				score += 40;
				immuneCoins.remove(i);
				yellowGhost.changeImg("immuneminecraftghost.png");
				redGhost.changeImg("immuneminecraftghost.png");
				blueGhost.changeImg("immuneminecraftghost.png");
				pinkGhost.changeImg("immuneminecraftghost.png");
				if(!isImmune) {
					isImmune = true;
				}
				else {
					endImmune += 10000;
				}
			}
		}
		
		if(isImmune) {
			endImmune = beginningImmune + 10000;
			if(System.currentTimeMillis() >= endImmune && isImmune) {
				stopImmune = true;
				System.out.println("hi");
				isImmune = false;
			}
		}
		
		if(stopImmune) {
			yellowGhost.changeImg("yellowminecraftghost.png");
			yellowGhost.setX(250);
			yellowGhost.setY(250);
			redGhost.changeImg("redminecraftghost.png");
			redGhost.setX(250);
			redGhost.setY(400);
			blueGhost.changeImg("blueminecraftghost.png");
			blueGhost.setX(350);
			blueGhost.setY(250);
			pinkGhost.changeImg("pinkminecraftghost.png");
			pinkGhost.setX(350);
			pinkGhost.setY(400);
			stopImmune = false;
		}
		
		
		if (fruit1.hitPlayer(player)) {
			score += 100;
			fruit1.setX(1000);
			fruit2.setY(0);
			fruitSound.play();
		}

		if (fruit2.hitPlayer(player)) {
			score += 100;
			fruit2.setX(1000);
			fruit2.setY(0);
			fruitSound2.play();
		}
		
		
		if(yellowGhost.hitPlayer(player)) {
			if(!isImmune) {
				lives--;
				player.reset();
				deadSound.play();
			}
			else {
				score += 50;
				yellowGhost.setX(10000);
			}
		}
		
		if(pinkGhost.hitPlayer(player)) {
			if(!isImmune) {
				lives--;
				player.reset();
				deadSound2.play();
			}
			else {
				score += 50;
				pinkGhost.setX(10000);
			}
		}
		
		if(blueGhost.hitPlayer(player)) {
			if(!isImmune) {
				lives--;
				player.reset();
				deadSound3.play();
			}
			else {
				score += 50;
				blueGhost.setX(10000);
			}
		}
		
		if(redGhost.hitPlayer(player)) {
			if(!isImmune) {
				lives--;
				player.reset();
				deadSound4.play();
			}
			else {
				score += 50;
				redGhost.setX(10000);
			}
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
		
		/*for(int i = 0; i < wallNames.size(); i++) {
			for(Walls temp: wallNames.get(i)) {
				if(temp.hitGhost(yellowGhost)) {
					rand = (int)(Math.random()*3) + 1;
				}
			}
		}*/
		
		if(num == 15) {
			ArrayList<Integer> check = new ArrayList<Integer>();
			for(int i = 0; i < wallNames.size(); i++) {
				check.add(yellowGhost.checkMove(wallNames.get(i)));
			}
			
			check.add(yellowGhost.checkMoveSingle(barrier1));
			check.add(yellowGhost.checkMoveSingle(barrier3));
			
			ArrayList<Integer> canMove = new ArrayList<Integer>();
			for (int i = 1; i < 5; i++) {
				boolean hasNum = false;
				for (int j = 0; j < check.size(); j++) {
					if (check.get(j) == i) {
						hasNum = true;
					}
				}
				if (hasNum == false) {
					canMove.add(i);
				}
			}
			
			int rand = (int)(Math.random()*4) + 1;
			yellowGhost.move1(rand, canMove);
			//yellowGhost.moveLeft();
			
			num = 0;
		}
		
		num++;
		
		if (num2 == 15) {
			ArrayList<Integer> check = new ArrayList<Integer>();
			
			for (int i = 0; i < wallNames.size(); i++) {
					check.add(redGhost.checkMove(wallNames.get(i)));
			}
			check.add(redGhost.checkMoveSingle(barrier1));
			check.add(redGhost.checkMoveSingle(barrier3));
			ArrayList<Integer> canMove = new ArrayList<Integer>();
			for (int i = 1; i < 5; i++) {
				boolean hasNum = false;
				for (int j = 0; j < check.size(); j++) {
					if (check.get(j) == i) {
						hasNum = true;
					}
				}
				if (hasNum == false) {
					canMove.add(i);
				}
			}

			int rand = (int)(Math.random()*4) + 1;
			redGhost.move1(rand, canMove);
			
			num2 = 0;

		}
		num2++;
		
		
		if (num3 == 15) {
			ArrayList<Integer> check = new ArrayList<Integer>();
			
			for (int i = 0; i < wallNames.size(); i++) {
					check.add(pinkGhost.checkMove(wallNames.get(i)));
			}
			
			check.add(pinkGhost.checkMoveSingle(barrier1));
			check.add(pinkGhost.checkMoveSingle(barrier3));
			
			ArrayList<Integer> canMove = new ArrayList<Integer>();
			for (int i = 1; i < 5; i++) {
				boolean hasNum = false;
				for (int j = 0; j < check.size(); j++) {
					if (check.get(j) == i) {
						hasNum = true;
					}
				}
				if (hasNum == false) {
					canMove.add(i);
				}
			}

			int rand = (int)(Math.random()*4) + 1;
			pinkGhost.move1(rand, canMove);
			
			num3 = 0;

		}
		num3++;
		
		if (num4 == 15) {
			ArrayList<Integer> check = new ArrayList<Integer>();
			
			for (int i = 0; i < wallNames.size(); i++) {
					check.add(blueGhost.checkMove(wallNames.get(i)));
			}
			
			check.add(blueGhost.checkMoveSingle(barrier1));
			check.add(blueGhost.checkMoveSingle(barrier3));
			
			ArrayList<Integer> canMove = new ArrayList<Integer>();
			for (int i = 1; i < 5; i++) {
				boolean hasNum = false;
				for (int j = 0; j < check.size(); j++) {
					if (check.get(j) == i) {
						hasNum = true;
					}
				}
				if (hasNum == false) {
					canMove.add(i);
				}
			}

			int rand = (int)(Math.random()*4) + 1;
			blueGhost.move1(rand, canMove);
			
			num4 = 0;

		}
		num4++;
		
		
		//g.fillOval(0, 0, 200, 200);
		
		int win = 0;
		if(count >= 97) {
			win = 1;
		}
		
		if(win == 1) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 650, 728);
			g.setColor(Color.white);
			g.setFont(biggest);
			g.drawString("You Win! :)", 40, 300);
			g.setFont(font2);
			g.drawString("Rerun to Play Again", 85, 350);
		}
		
		if(lives <= 0) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 650, 728);
			g.setColor(Color.white);
			g.setFont(biggest);
			g.drawString("You Lose", 90, 320);
			g.setFont(font2);
			g.drawString("Rerun to Play Again", 110, 350);
		}
		
	}
	
	public Driver () {
		JFrame f = new JFrame("Example Drawing");
		f.setSize(650, 728);
		f.setResizable(false);
		soundBackground.play();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.add(this);
		f.addKeyListener(this);
		
		background = new Background("startScreen.png", 0,0);
		background2 = new Background("background.png",0,0);
		player = new Player();
		fruit1 = new Fruit();
		fruit2 = new Fruit(450, 550);
		/*immuneCoin1 = new ImmunityCoin(100, 50);
		immuneCoin2 = new ImmunityCoin(500, 250);
		immuneCoin3 = new ImmunityCoin(200, 500);*/
		yellowGhost = new Ghost("yellowminecraftghost.png", 250, 250, 0);
		blueGhost = new Ghost("blueminecraftghost.png", 350, 250, 0);
		pinkGhost = new Ghost("pinkminecraftghost.png", 350, 400, 0);
		redGhost = new Ghost("redminecraftghost.png", 250, 400, 0);
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
		
		wallNames.add(upperWalls);
		wallNames.add(rightWalls);
		wallNames.add(leftWalls);
		wallNames.add(barrier2);
		wallNames.add(barrier4);
		wallNames.add(barrier5);
		wallNames.add(barrier6);
		wallNames.add(barrier7);
		wallNames.add(barrier8);
		wallNames.add(barrier9);
		wallNames.add(barrier10);
		wallNames.add(barrier11);
		wallNames.add(barrier12);
		wallNames.add(barrier13);
		wallNames.add(barrier14);
		wallNames.add(barrier15);
		wallNames.add(barrier16);
		wallNames.add(barrier17);
		wallNames.add(bottomWalls);
		
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
		for(int i = 0; i < barrier16.length; i++) {
			barrier16[i] = new Walls(250,300);
			barrier16[i].setX(250 + 50*i);
		}
		for(int i = 0; i < barrier17.length; i++) {
			barrier17[i] = new Walls(250,350);
			barrier17[i].setX(250 + 50*i);
		}
		for(int i = 0; i < bottomWalls.length; i++) {
			bottomWalls[i] = new Walls(0, 700);
			bottomWalls[i].setX(50*i);
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
		
		//add this panel to the JFrame
		//allows connection with "drawing"
		
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
		
		
		for (int i = 0; i < wallNames.size(); i++) {
			for (Walls temp: wallNames.get(i)) {
				if(temp.hitPlayer(player) && e.getKeyCode() == 39) {
					player.moveLeft();
				}
				if(temp.hitPlayer(player) && e.getKeyCode() == 40) {
					player.moveUp();
			}
				if(temp.hitPlayer(player) && e.getKeyCode() == 38) {
					player.moveDown();
				}
				if(temp.hitPlayer(player) && e.getKeyCode() == 37) {
					player.moveRight();
				}
				
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