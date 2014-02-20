package graphic;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class PlayerMouseListener implements MouseListener{
	Player player;
	
	PlayerMouseListener(Player player)
	{
		this.player=player;
	}
	
    public void mouseClicked(MouseEvent e) {
      //System.out.println("Mouse has been clicked");
    }   

    public void mousePressed(MouseEvent e) {
    	if(e.getButton()==e.BUTTON1)
    	{
    		player.stopFlyingDown();
    		player.flyUp();
    	}
    	if(e.getButton()==e.BUTTON3)
    	{
    		this.player.activateBoost();
    	}
    }

    public void mouseReleased(MouseEvent e) {
    	if(e.getButton()==e.BUTTON1)
    	{
    		player.stopFlyingUp();
    		player.flyDown();
    	}
    	if(e.getButton()==e.BUTTON3)
    	{
    		this.player.deactiveteBoost();
    	}
    }

    public void mouseEntered(MouseEvent e) {
    	
    }

    public void mouseExited(MouseEvent e) {
    	
    }
 }