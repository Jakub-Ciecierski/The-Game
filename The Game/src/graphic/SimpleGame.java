package graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    JFrame app;
	Canvas canvas;
	BufferStrategy bufferStrategy;
	GraphicsEnvironment graphicsEnviroment;
	GraphicsDevice graphicsDevice;
    GraphicsConfiguration graphicsConfiguration;
    BufferedImage offScreenImage;
    Graphics graphics;
    Graphics2D g2d;
    Color backgroundColor;
    
    Player player;
   
    
	public SimpleGame(int width, int height) {
		
		super();
		this. width = width;
		this.height = height;
		this.fps = 0;
	    this.frames = 0;
	    this.totalTime = 0;
	    this.curTime = System.currentTimeMillis();
	    this.lastTime = curTime;
		this.player = new Player(50,50);
		
		
		createGameWindow();
		createCanvas();
		addCanvasToWindow();
		createBackBufferStrategy();
		setGraphicsConfiguration();
		createOffScreen();
		
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
		this.canvas.addMouseListener(new PlayerMouseListener());
	}
    
    
    private void addCanvasToWindow(){
    	
		this.app.add( this.canvas );
		this.app.pack();
		this.app.setVisible( true );

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
		    
		    // add moving player
		    this.player.refreshPlayer(this.g2d);
		    this.g2d.setColor( Color.BLUE );
		    this.g2d.fillRect(width/2, height/2, 50, 50);
		    
		    	
		    // display frames per second...
		    this.g2d.setFont( new Font( "Courier New", Font.PLAIN, 12 ) );
		    this.g2d.setColor( Color.GREEN );
		    this.g2d.drawString( String.format( "FPS: %s", fps ), 20, 20 );
		                
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
    
 
	class PlayerMouseListener implements MouseListener{
	
	    public void mouseClicked(MouseEvent e) {
	      //System.out.println("Mouse has been clicked");
	    }   

	    public void mousePressed(MouseEvent e) {
	    	if(e.getButton()==e.BUTTON1)
	    	{
	    		player.stopFlyingDown();
	    		player.flyUp();
	    	}
	    }

	    public void mouseReleased(MouseEvent e) {
	    	if(e.getButton()==e.BUTTON1)
	    	{
	    		player.stopFlyingUp();
	    		player.flyDown();
	    	}
	    }

	    public void mouseEntered(MouseEvent e) {
	    	
	    }

	    public void mouseExited(MouseEvent e) {
	    	
	    }
	 }

	
	public static void main(String[] args) {
		SimpleGame simpleGame = new SimpleGame(780,400);
		simpleGame.start();
	}
}