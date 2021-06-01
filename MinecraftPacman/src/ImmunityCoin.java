import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class ImmunityCoin {
	
	private int x, y; // Position of background
	private int width; // the size of player
	private int height;
	
	private Image imCoin; // image of the player
	private Image img;
	
	public ImmunityCoin() {
		// assignment statements for attributes
		x = 300;
		y = 300;
		width = 50;
		height = 50;
		imCoin = getImage("immunitycoin.png");
		img = imCoin;
		init(x, y); //call init every time x, y of image is being set

	}
	
	/* if filename is provided */
	public ImmunityCoin(int x, int y) {
		// assignment statements for attributes
		this.x = x;
		this.y = y;
		width = 50;
		height = 50;
		imCoin = getImage("immunitycoin.png");
		img = imCoin;
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

	public Rectangle getRect() {
		Rectangle temp = new Rectangle(x,y,width,height);
		return temp;
	}
	public boolean hitPlayer(Player p) {
		Rectangle temp = this.getRect();
		Rectangle player = new Rectangle(p.getX(),p.getY(),p.getWidth(),p.getHeight());
		return temp.intersects(player);
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