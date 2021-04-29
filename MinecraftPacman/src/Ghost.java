
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Ghost {
	private int x, y;
	private int vx, vy;
	private int width, height;
	
	private Image img;
	
	public Ghost() {
		
		/*
		x =  ;
		y =  ;
		vx = ;
		vy = ;
		width = ;
		height = ;
		img = getImage("???");	
	
		*/
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(img, tx, null);
		
	}
	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	
	
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setVx(int vx) {
		this.vx = vx;
	}
	
	public int getVx() {
		return this.vx;
	}
	public Rectangle getRect() {
		Rectangle temp = new Rectangle(x,y,width,height);
		return temp;
	}
}
