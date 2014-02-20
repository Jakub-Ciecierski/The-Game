package graphic;

public class FlyingUp extends Thread
	{
		private Player player;
		private int speed;
		private Thread T;
		
		FlyingUp(Player player)
		{
			this.player=player;
			this.speed=1;
			T=null;
		}
		
		int getSpeed()
		{
			return this.speed;
		}
		
		void setSpeed(int n)
		{
			this.speed=n;
		}
		
		public boolean isActive()
		{
			return (T==null?false:true);
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
				player.decrementY(getSpeed());
				
				try {
					player.flyingUp.sleep(8);
				} catch (InterruptedException e) {
					break;
					}
				
				if(isInterrupted())
					break;
			}
		}
	}