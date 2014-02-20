package graphic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerKeyListener implements KeyListener{
	Player player;
	
	PlayerKeyListener (Player player)
	{
		this.player=player;
	}
	
	public void keyPressed(KeyEvent e) {
		/*if(e.getKeyCode()==e.VK_SPACE)
			this.player.activateBoost();*/
		
	}

	public void keyReleased(KeyEvent e) {
		/*if(e.getKeyCode()==e.VK_SPACE)
			this.player.deactiveteBoost();*/
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
