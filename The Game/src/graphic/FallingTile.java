package graphic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FallingTile {
	
	int x;
	int y;
	int speedX;
	int speedY;
	BufferedImage image;

	public FallingTile(int x, int y, BufferedImage img) {
		super();
		this.x = x;
		this.y = y;
		
		this.speedX = randomizeSpeed();
		this.speedY = randomizeSpeed();
		this.image = img;
	
		
	}
	
	public void render(Graphics2D g2d){
		g2d.drawImage(image,this.x, this.y, null);
	}
	
	private int randomizeSpeed(){
		return  (int )(Math.random() * 5 + 1);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void incrementX(){
		this.x -= this.speedX;
	}
	public void incrementY(){
		this.y += this.speedY;
		
	}
	
}
