package graphic;

public class FlyingDown extends Thread
{
	private Player player;
	private Thread T;
	private int speed;
	FlyingDown(Player player)
	{
		this.player=player;
		this.speed=2;
	}
	
	int getSpeed()
	{
		return this.speed;
	}
	
	public void setSpeed(int n)
	{
		this.speed=n;
	}
	
	public void init()
	{
		T = new Thread(this);
		T.start();
	}
	
	public void stopThread()
	{
		if(T!=null)
		{
			T.interrupt();
			T=null;
		}
	}
	
	public void run()
	{
		while(true)
		{
			player.incrementY(getSpeed());
			
			try {
				player.flyingDown.sleep(8);
			} catch (InterruptedException e) {
				break;
			}
		
			if(isInterrupted())
				break;
		}
	}
}