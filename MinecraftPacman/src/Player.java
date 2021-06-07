import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Player {
	// attributes of a player
		private int x, y; // Position of player
		private int vx, vy;
		private int width; // the size of player
		private int height;
		
		private Image chicken; // image of the player
		private Image img;
		private int step = 50;

		public Player() {
			// assignment statements for attributes
			x = 300;
			y = 650;
			vx = 0;
			vy = 0;
			width = 50;
			height = 50;
			chicken = getImage("forwardminecraftchicken.png");
			img = chicken;
			//init(x, y); //call init every time x, y of image is being set

		}
		
		/* if filename is provided */
		public Player(String fileName, int x, int y, int vx) {
			// assignment statements for attributes
			this.x = x;
			this.y = y;
			this.vx = vx;
			vy = 0;
			width = 50;
			height = 50;
			img = getImage(fileName);
			//init(x, y);

		}
		
		

		public void reset() {
			x = 300;	//reset position
			y = 650;
			img = chicken;	//reset img
			vx = 0;
		}


		// gets image and process it
		public void move() {
			
			y += vy;
			x += vx;
			tx.setToTranslation(x, y);

		}

		
		
		private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

		// draw the affine transform
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			move(); //ask frog to update its location variables
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

		public int getVx() {
			return vx;
		}
		
		public int getVy() {
			return vy;
		}
		
		public void setVx(int vx) {
			this.vx = vx;
		}
		

		public void setVy(int vy) {
			this.vy = vy;
			
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
		
		//movement methods
		public void moveUp() {
			y -= step;
			tx.setToTranslation(x, y);
			img = getImage("forwardminecraftchicken.png");
			
			
		}
		
		public void moveDown() {
			y += step;
			tx.setToTranslation(x, y);
			img = getImage("backwardsminecraftchicken.png");
		}
		
		public void moveRight() {
			x += step;
			tx.setToTranslation(x, y);
			img = getImage("rightminecraftchicken.png");
		}
		
		public void moveLeft() {
			x -= step;
			tx.setToTranslation(x, y);
			img = getImage("leftminecraftchicken.png");
		}
		
		/* Helper function for collision detection later */
		public Rectangle getRect() {
			Rectangle temp = new Rectangle(x,y,width,height);
			return temp;
		}

}