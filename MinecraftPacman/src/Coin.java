
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Coin {
	
	private int x, y; // Position of background
	private int width; // the size of player
	private int height;
	
	private Image coin; // image of the player
	private Image img;
	
	public Coin() {
		// assignment statements for attributes
		x = 100;
		y = 100;
		width = 50;
		height = 50;
		coin = getImage("minecraftcoin.png");
		img = coin;
		init(x, y); //call init every time x, y of image is being set

	}
	
	/* if filename is provided */
	public Coin(String fileName, int x, int y, int vx) {
		// assignment statements for attributes
		this.x = x;
		this.y = y;
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
