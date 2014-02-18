package graphic;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Player {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color playerColor;
	Thread flyingUp;
	Thread flyingDown;
	boolean isInit;
	
	public Player(int width, int height)
	{
		this.width=width;
		this.height=height;
		this.x=0;
		this.y=0;
		this.playerColor=Color.red;
		isInit=true;
	}
	
	
	public void refreshPlayer(Graphics2D g2d)
	{
		g2d.setColor(playerColor);
		g2d.fillRect(x,y,width,height);
		
		// Reset the coords
		if(x>779)
			x=0;
		if(y>399)
			y=0;
		if(y<0-height)
			y=399;
		
		// Checks if the function is run for the first time, then init FlyingDown thread
		if(isInit)		
		{
			flyDown();
			isInit=false;
		}
		
	}
	public void flyDown()
	{ 
		flyingDown = new FlyingDown();
		flyingDown.start();
	}
	
	
	public void stopFlyingDown()
	{
		if(flyingDown!=null)
		{
			flyingDown.interrupt();
			flyingDown=null;
		}
	}
	
	
	public void flyUp()
	{
		flyingUp = new FlyingUp();
		flyingUp.start();
	}
	
	public void stopFlyingUp()
	{
		if(flyingUp!=null)
		{
			flyingUp.interrupt();
			flyingUp=null;
		}
	}
	
	class FlyingDown extends Thread
	{
		public void run()
		{
			while(true)
			{
				x++;
				y++;
				System.out.println("flying down");
				
				try {
					flyingDown.sleep(5);
				} catch (InterruptedException e) {
					break;
				}
				
				if(isInterrupted())
					break;
			}
		}
	}
	
	class FlyingUp extends Thread
	{
		public void run()
		{
			while(true)
			{
				y--;
				x++;
				System.out.println("flying up");
				try {
					flyingUp.sleep(5);
				} catch (InterruptedException e) {
					break;
					}
				
				if(isInterrupted())
					break;
			}
		}
	}
	
}
