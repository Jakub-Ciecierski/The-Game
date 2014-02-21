package graphic;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class FallingTilesManager {

	boolean isFull;
	FallingTile fallingTile1;
	
	public FallingTilesManager(){
		this.isFull = false;
	}
	
	public void refresh(Graphics2D g2d){
		
	}
	
	public void addTile(){
		this.isFull = true;
		
	}
}
