package graphic;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class BitMap
{
	private BufferedImage hero;
	private BufferedImage background;
	private BufferedImage obstacle;
	
	BitMap()
	{
		init();
	}
 
	public BufferedImage getObstacle()
	{
		return this.obstacle;
	}
	
	public BufferedImage getHero()
	{
		return this.hero;
	}
	
	public BufferedImage getBackground()
	{
		return this.background;
	}
	
	private void init()
	{ 
		try 
		{
			hero = ImageIO.read( getClass().getResource("/images/hero2.png") );
			background = ImageIO.read( getClass().getResource("/images/bc.png") );
			obstacle= ImageIO.read( getClass().getResource("/images/obstacle.png") );
		}
		catch(IOException e) {
			System.out.println("Load Image error:");
		}
	} // end of init()
}