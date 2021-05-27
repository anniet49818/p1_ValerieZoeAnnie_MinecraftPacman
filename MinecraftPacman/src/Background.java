import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background {
		private int x, y; 

		private Image background; //image of the player
		private Image img; 


		private int width; // the size of player
		private int height;
		
		
		public Background() {
			// assignment statements for attributes
			x = 0;
			y = 0;
			width = 600;
			height = 700;
			background = getImage("background.png");
			img = background;
			init(x, y); //call init every time x, y of image is being set

		}
		
		/* if filename is provided */
		public Background(String fileName, int startx, int starty) {
			// assignment statements for attributes
			x = starty;
			y = startx;
			
			img = getImage(fileName);
			init(x, y);
		}

		/* if filename is provided */
		public Background(String fileName, int x, int y, int vx) {
			// assignment statements for attributes
			this.x = x;
			this.y = y;
			width = 600;
			height = 700;
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


		private Image getImage(String path) {
			Image tempImage = null;
			try {
				URL imageURL = Background.class.getResource(path);
				tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tempImage;
		}
		
		public void hide() {
			img = null;
		}

		public void setImg(String img) {
			this.img = getImage(img);
		}

		// setters and getters


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
		

	
}
