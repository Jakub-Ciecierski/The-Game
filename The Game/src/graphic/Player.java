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
	
	public Player(int width, int height)
	{
		this.width=width;
		this.height=height;
		this.x=0;
		this.y=0;
		this.playerColor=Color.red;
	}
	
	public void flyDown(Graphics2D g2d)
	{
		g2d.setColor(playerColor);
		g2d.fillRect(x,y,width,height);
		x++;
		y++;
		if(x>779)
			x=0;
		if(y>399)
			y=0;
		if(y<0)
			y=399;
		try {
			Thread.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
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
		Graphics2D g2d;
		FlyingDown (Graphics2D g2d)
		{
			this.g2d=g2d;
		}
		public void run()
		{
			System.out.println("Flying down started");
			while(true)
			{
				try {
					flyingDown.sleep(500);
				} catch (InterruptedException e) {
					break;
				}
				g2d.setColor(playerColor);
				g2d.fillRect(x,y,width,height);
				x++;
				y++;
				System.out.println(x+" : "+y);
				if(isInterrupted())
					break;
			}
			System.out.println("Flying down stoped");
		}
	}
	
	class FlyingUp extends Thread
	{
		public void run()
		{
			while(true)
			{
				y-=1;
				try {
					flyingUp.sleep(3);
				} catch (InterruptedException e) {
					break;
					}
				
				if(isInterrupted())
					break;
			}
		}
	}
	
}
