package graphic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Vertex {
	/*
	 * type 0 background, 1 obstacle, 2 falling tile
	 */
	int type;
	int x;
	int y;
	int number;
	BufferedImage[] v_listOfImages;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Vertex(int number, int type, int x, int y, BufferedImage[] listOfImages) {
		super();
		this.type = type;
		this.x = x;
		this.y = y;
		this.number = number;
		this.v_listOfImages = listOfImages;
	}
	
	public void render(Graphics2D g2d)
	{
		switch (this.type) {
		case 0:
			g2d.drawImage(this.v_listOfImages[0],this.y, this.x, null);
			break;
		case 1:
			g2d.drawImage(this.v_listOfImages[1],this.y, this.x, null);
			break;
		default:
			break;
		}
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	
	public void decrementationX(){
		this.x -= 1;
	}
	public void decrementationY(){
		this.y -= 1;
	}
	
}
