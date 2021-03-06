package graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class SimpleGame {
	
	int width;
    int height;
    int fps;
    int frames;
    long totalTime;
    long curTime;
    long lastTime;
    int speed;
    
    JFrame app;
	Canvas canvas;
	BufferStrategy bufferStrategy;
	GraphicsEnvironment graphicsEnviroment;
	GraphicsDevice graphicsDevice;
    GraphicsConfiguration graphicsConfiguration;
    BufferedImage offScreenImage;
    Image backgroundSpace;
    Graphics graphics;
    Graphics2D g2d;
    Color backgroundColor;
    
    Player player;
    SlowMotion slowMotion;
    Graph map;
   
    
	public SimpleGame(int width, int height) {
		
		super();
		this. width = width;
		this.height = height;
		this.fps = 0;
	    this.frames = 0;
	    this.totalTime = 0;
	    this.curTime = System.currentTimeMillis();
	    this.lastTime = curTime;
	    this.player = new Player(30,40,this.width,this.height);
		this.map = new Graph(this.width+60,this.height,30,40,g2d);
		
		this.speed =2;
		this.slowMotion=new SlowMotion(this);
		
		createGameWindow();
		createCanvas();
		addCanvasToWindow();
		createBackBufferStrategy();
		setGraphicsConfiguration();
		createOffScreen();
		createBackgroundGif();
		
	}
    
	private void createBackgroundGif() {
		
		Toolkit t = Toolkit.getDefaultToolkit ();
	    this.backgroundSpace = t.createImage("starsNew.gif");
		
	}

	public void setSpeed(int s)
	{
		this.speed=s;
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public void activateSlowMotion()
	{
		slowMotion.init();
	}
	
	private void createOffScreen() {
		
		this.offScreenImage = 
				this.graphicsConfiguration.createCompatibleImage
					(this.width, this.height);
		
	}

	private void setGraphicsConfiguration() {
		
		this.graphicsEnviroment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    this.graphicsDevice = this.graphicsEnviroment.getDefaultScreenDevice();
	    this.graphicsConfiguration = this.graphicsDevice.getDefaultConfiguration();
	    //--------------------------------
	    this.graphics = null;
	    this.g2d = null;
	    this.backgroundColor = Color.BLACK;
		
	}
	
	private void createBackBufferStrategy() {
		
		this.canvas.createBufferStrategy(2);
		this.bufferStrategy = canvas.getBufferStrategy();
	    
	}

	private void createGameWindow(){
    	
		this.app = new JFrame();
		this.app.setIgnoreRepaint(true);
		this.app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
    }
    
    private void createCanvas(){
    	
		this.canvas = new Canvas();
		this.canvas.setIgnoreRepaint(true);
		this.canvas.setSize( this.width, this.height );
		this.canvas.addMouseListener(new PlayerMouseListener(this.player));
		this.canvas.addKeyListener(new PlayerKeyListener(this));
	}
    
    
    private void addCanvasToWindow(){
    	
		this.app.add( this.canvas );
		this.app.pack();
		this.app.setVisible( true );

    }
    
   
    private boolean checkColision()
    {
    	for(int i=0;i<this.map.listOfVertices.length;i++)
    		if(this.map.listOfVertices[i].getType()==1)
    			if((this.map.listOfVertices[i].getY()-this.player.getX()<=this.player.getWidth())&&(this.map.listOfVertices[i].getY()+this.player.getWidth()>this.player.getX()) && 
    					(this.map.listOfVertices[i].getX()-this.player.getY()<=this.player.getHeight()) && (this.map.listOfVertices[i].getX()+this.player.getHeight()>this.player.getY()))
    					return true;
    	return false;
    }
    
    public void start(){
    	
    	while( true ) {
		
		  try {
		
		    // count Frames per second...
		    this.lastTime = this.curTime;
		    this.curTime = System.currentTimeMillis();
		    this.totalTime += this.curTime - this.lastTime;
		    
		    if( this.totalTime > 1000 ) {
		
		      this.totalTime -= 1000;
		
		      this.fps = this.frames;
		
		      this.frames = 0;
		
		    } 
		
		    ++(this.frames);
		    
		    // clear back buffer...
		    this.g2d = this.offScreenImage.createGraphics();
		    this.g2d.setColor( this.backgroundColor );
		    this.g2d.fillRect( 0, 0, this.width, this.height );
		    
		    //draw background
		    //this.g2d.drawImage(this.backgroundSpace, 0, 0, canvas);
		    this.g2d.drawImage(this.backgroundSpace, 0, 0, app);
		    
		 
		    //display map
	        if(this.frames %this.getSpeed() == 0)
	        	this.map.updatePosition();
	        this.g2d.setColor( Color.DARK_GRAY);
	        this.map.draw(g2d);
		    
		    
		    // add moving player
		    this.player.refreshPlayer(this.g2d);
		    this.g2d.setColor( Color.BLUE );
		    
		    //if(checkColision())
		    	//break;
		    	
		    // display frames per second...
		    this.g2d.setFont( new Font( "Courier New", Font.PLAIN, 12 ) );
		    this.g2d.setColor( Color.GREEN );
		    this.g2d.drawString( String.format( "FPS: %s", fps ), 20, 20 );
		    this.g2d.drawString( "Score : "+ this.map.getScore(), 20, 80 );
		    // Blit image and flip...
		    this.graphics = this.bufferStrategy.getDrawGraphics();
		    this.graphics.drawImage( this.offScreenImage, 0, 0, null );
		
		    if( !this.bufferStrategy.contentsLost() )
		    		this.bufferStrategy.show();
		       
		    // Let the OS have a little time...
		    Thread.yield();
		
		  } finally {
		
		    // release resources
		    if( this.graphics != null ) 
		    	this.graphics.dispose();
		    if( this.g2d != null ) 
		        this.g2d.dispose();
		
		  }
		
		}
    }
 
	public static void main(String[] args) {
		
		SimpleGame simpleGame = new SimpleGame(780,400);
		simpleGame.start();
	}
}