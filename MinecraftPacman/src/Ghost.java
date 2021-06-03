
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Ghost {
	
	private int x, y; // Position of background
	private int width; // the size of player
	private int height;
	private int vx, vy;
	private int step = 50;
	
	private Image ghost; // image of the player
	private Image img;
	
	public Ghost() {
		// assignment statements for attributes
		x = 200;
		y = 300;
		width = 50;
		height = 50;
		vx = 0;
		vy = 0;
		ghost = getImage("yellowminecraftghost.png");
		img = ghost;
		init(x, y); //call init every time x, y of image is being set

	}
	
	/* if filename is provided */
	public Ghost(String fileName, int x, int y, int vx) {
		// assignment statements for attributes
		this.x = x;
		this.y = y;
		vy = 0;
		this.vx = vx;
		width = 50;
		height = 50;
		img = getImage(fileName);
		init(x, y);

	}
	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Player.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	// setters and getters
	public void reset() {
		x = 400;
		y = 610;
		vx = 0;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		tx.setToTranslation(x, y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		tx.setToTranslation(x, y);
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void move() {
		x += vx;
		y += vy;
		tx.setToTranslation(x, y); //image must be moved according to x, y updates
	}

	public void moveUp() {
		vy = -1;
		move();
		tx.setToTranslation(x, y);
	}
	
	public void moveDown() {
		vy = 1;
		move();
		tx.setToTranslation(x, y);
	}
	
	public void moveRight() {
		vx = 1;
		move();
		tx.setToTranslation(x, y);
	}
	
	public void moveLeft() {
		x = -1;
		move();
		tx.setToTranslation(x, y);
	}
	
	public void setVx(int vx) {
		this.vx = vx;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	/* Helper function for collision detection later */
	public Rectangle getRect() {
		Rectangle temp = new Rectangle(x ,y,width,height);
		return temp;
	}
	public boolean hitPlayer(Player p) {
		Rectangle temp = this.getRect();
		Rectangle player = new Rectangle(p.getX(),p.getY(),p.getWidth(),p.getHeight());
		return temp.intersects(player);
	}
	public boolean canMove(int direction, Walls w) {
		if(direction == 1) { //1 is right
			if(w.goingToHitGhostRight(this)) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(direction == 2) { //2 is left
			if(w.goingToHitGhostLeft(this)) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(direction == 3) { //3 is up
			if(w.goingToHitGhostUp(this)) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(direction == 4) { //4 is down
			if(w.goingToHitGhostDown(this)) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}
	
	
	
}