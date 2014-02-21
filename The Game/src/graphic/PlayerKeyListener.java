package graphic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerKeyListener implements KeyListener{
	SimpleGame simpleGame;
	
	PlayerKeyListener (SimpleGame simpleGame)
	{
		this.simpleGame = simpleGame;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==e.VK_SPACE)
			this.simpleGame.activateSlowMotion();
		
	}

	public void keyReleased(KeyEvent e) {
		/*if(e.getKeyCode()==e.VK_SPACE)
			this.player.deactiveteBoost();*/
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
