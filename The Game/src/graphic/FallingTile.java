package graphic;

public class FallingTile {
	
	int x;
	int y;
	int type;
	int speedX;
	int speedY;

	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		
	}

	public FallingTile(int x, int y, int type, String typeOfBehaviour) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		if(typeOfBehaviour == "RANDOMXY")
		{
			this.speedX = randomizeSpeed();
			this.speedY = randomizeSpeed();
		}
		if(typeOfBehaviour == "RANDOMY")
		{
			this.speedX = 1;
			this.speedY = randomizeSpeed();
		}
		if(typeOfBehaviour == "DEFAULT")
		{
			this.speedX = 1;
			this.speedY = 2;
		}
		
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
