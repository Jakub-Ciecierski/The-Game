package graphic;

public class Vertex {
	/*
	 * type 0 background, 1 obstacle, 2 falling tile
	 */
	int type;
	int x;
	int y;
	int number;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Vertex(int number, int type, int x, int y) {
		super();
		this.type = type;
		this.x = x;
		this.y = y;
		this.number = number;
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
