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
	
	int numImCoins = (int)(Math.random()*4) + 2;
	
	
	Background background;
	Player player;
	
	
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
	ImmunityCoin[] immuneCoins = new ImmunityCoin[numImCoins];
	Ghost yellowGhost;
	Ghost blueGhost;
	Ghost pinkGhost;
	Ghost redGhost;
	
	
	Font big = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);
	Font biggest = new Font("Courier New", 1, 80);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		background.paint(g);
		g.setColor(Color.gray);
		g.fillRect(200, 270, 400, 50);
		g.setColor(Color.white);
		g.setFont(font2);
		g.drawString("Press Space to Start", 220, 300);
		
		
		for(RegCoin[] temp: coins) {
			for(RegCoin temp1: temp) {
				temp1.paint(g);
			}
		}
		player.paint(g);
		fruit1.paint(g);
		fruit2.paint(g);
		barrier1.paint(g);
		barrier3.paint(g);
		
		for(ImmunityCoin temp: immuneCoins) {
			temp.paint(g);
		}
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
		
		
		//g.fillOval(0, 0, 200, 200);
	}
	
	public Driver () {
		JFrame f = new JFrame("Minecraft Pacman");
		f.setSize(650, 728);
		f.setResizable(false);

		
		//set default action for x button
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add this panel to the JFrame
		//allows connection with "drawing"
		f.add(this);
		f.addKeyListener(this);
		
		background = new Background("startScreen.png", 0, 0);
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
		
		for(int i = 0; i < immuneCoins.length; i++) {
			int x = 200;
			int y = 200;
			while((x == 200 && y == 200) || (x == 450 && y == 550) || (x > 250 && x < 400 && y > 300 && y < 400)) {
				x = (int)(Math.random()*12) + 1;
				y = (int)(Math.random()*13) + 1;
			}
			immuneCoins[i] = new ImmunityCoin(x*50, y*50);
			
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		if(e.getKeyCode() == 38){	//up
			player.moveUp();
		}
		if(e.getKeyCode() == 39){	//right
			player.moveRight();
		}
		if(e.getKeyCode() == 37){	//left
			player.moveLeft();
		}
		if(e.getKeyCode() == 40) {
			player.moveDown();
		}
		if(e.getKeyCode() == 32) {
			background.setImg("background.png");
			
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
