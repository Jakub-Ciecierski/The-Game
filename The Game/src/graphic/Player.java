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
	private int speed;
	
	private Color playerColor;
	private boolean isInit;
	private int state;
	private int screenWidth;
	private int screenHeight;
	Thread flyingUp;
	Thread flyingDown;
	
	public Player(int width, int height,int screenWidth, int screenheight)
	{
		this.width=width;
		this.height=height;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.x= this.screenWidth/2 - this.width/2;
		this.y= this.screenHeight/2 - this.height/2;
		this.y=0;
		this.playerColor=Color.red;
		isInit=true;
		this.state=0;
		
		// player starts flying down
		this.state=0;
	}
	
	int getX()
	{
		return x;
	}
	
	int getY()
	{
		return y;
	}
	
	/*Returns the State of player
	flying up 1,flying down 0*/
	int getState()
	{
		return state;
	}
	
	int[] getPosition()
	{
		int[] positions ={x,y,x+width,y+height};
		return positions; 
	}
	
	int getSpeed()
	{
		return this.speed;
	}
	
	void setSpeed(int speed)
	{
		this.speed=speed;
	}
	
	void setState(int state)
	{
		this.state = state;
	}
	
	
	public void refreshPlayer(Graphics2D g2d)
	{
		g2d.setColor(playerColor);
		g2d.fillRect(x,y,width,height);
	
		g2d.setFont( new Font( "Courier New", Font.PLAIN, 16 ) );
	    g2d.setColor( Color.GREEN );
		if(getState()==0)
			g2d.drawString("Flying down", 20, 35);
		else
			g2d.drawString("Flying up", 20, 35);
		
		g2d.drawString("x: "+x+" y: "+y, 20, 50);
		
		
		// Reset the coords
		/*if(x>779-width)
			x=779;//x=-width;*/
		if(y>399-height)
			y=399-height;//y=-height;
		if(y<0)
			y=0;//y=399;
		
		// Checks if the function is ran for the first time.
		if(isInit)		
		{
			flyDown();
			isInit=false;
		}
		
	}
	public void flyDown()
	{ 
		setState(0);
		flyingDown = new FlyingDown();
		flyingDown.start();
	}
	
	
	public void stopFlyingDown()
	{
		if(flyingDown!=null)
		{
			setState(1);
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
				//x++;
				y++;
				try {
					flyingDown.sleep(6);
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
				//x++;
			
				try {
					flyingUp.sleep(6);
				} catch (InterruptedException e) {
					break;
					}
				
				if(isInterrupted())
					break;
			}
		}
	}
	
}
