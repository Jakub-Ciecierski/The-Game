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
	
	FlyingUp flyingUp;
	FlyingDown flyingDown;
	SpeedBoost speedBoost;

	
	boolean isBoostUp;
	
	BitMap bitMap;
	
	public Player(int width, int height,int screenWidth, int screenHeight,BitMap bitMap)
	{
		this.width=width;
		this.height=height;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.x= this.screenWidth/2 - this.width/2;
		this.y= this.screenHeight/2 - this.height/2;
		
		this.playerColor=Color.red;
		isInit=true;
		isBoostUp=false;
		
		flyingUp=new FlyingUp(this);
		flyingDown=new FlyingDown(this);
		speedBoost = new SpeedBoost(this);
		// player starts flying down
		this.state=0;
		this.bitMap= bitMap;
		
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	/*Returns the State of player
	speed boost 2,flying up 1,flying down 0*/
	public int getState()
	{
		return state;
	}
	
	public int[] getPosition()
	{
		int[] positions ={x,y,x+width,y+height};
		return positions; 
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public void setSpeed(int speed)
	{
		this.speed=speed;
	}
	
	public void setState(int state)
	{
		this.state=state;
	}
	
	public void decrementX(int n)
	{
		this.x=this.x-n;
	}
	
	public void incrementX(int n)
	{
		this.x=this.x+n;
	}
	
	public void decrementY(int n)
	{
		this.y=this.y-n;
	}
	
	public void incrementY(int n)
	{
		this.y=this.y+n;
	}
	
	public void refreshPlayer(Graphics2D g2d)
	{
		g2d.setColor(playerColor);
		//g2d.fillRect(x,y,width,height);
		g2d.drawImage(bitMap.getHero(), x, y, null);
		
		g2d.setFont( new Font( "Courier New", Font.PLAIN, 16 ) );
	    g2d.setColor( Color.GREEN );
		if(getState()==0)
			g2d.drawString("Flying down", 20, 35);
		if(getState()==1)
			g2d.drawString("Flying up", 20, 35);
		if(getState()==2)
			g2d.drawString("Speed Boost!", 20, 35);
		
		g2d.drawString("x: "+x+" y: "+y, 20, 50);
		g2d.drawString("Boost Fuel: "+speedBoost.getBoostFuel(),20,65);
		
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
		flyingDown.init();
	}
	
	
	public void stopFlyingDown()
	{
		
		flyingDown.stopThread();
	}
	
	public void flyUp()
	
	{
		setState(1);
		flyingUp.init();
	}
	
	public void stopFlyingUp()
	{
		flyingUp.stopThread();
	}
	
	public void activateBoost()
	{
		speedBoost.init();
	}
	
	public void deactiveteBoost()
	{
		speedBoost.stopThread();
	}
	
}
