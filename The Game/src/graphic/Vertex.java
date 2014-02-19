package graphic;

public class Vertex {
	
	String type;
	int x;
	int y;
	int number;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Vertex(int number, String type, int x, int y) {
		super();
		this.type = type;
		this.x = x;
		this.y = y;
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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
