package graphic;

public class SlowMotion	extends Thread {
	SimpleGame simpleGame;
	Thread T;
	
	SlowMotion(SimpleGame simpleGame)
	{
		this.simpleGame = simpleGame;
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
		long startTime = System.currentTimeMillis();
		this.simpleGame.setSpeed(10);
		long endTime = 0;
		while(true)
		{
			endTime=System.currentTimeMillis();
			double passedTime =(double)(endTime-startTime)/1000;
			if(passedTime>2)
				break;
			try {
				T.sleep(1);
			} catch (InterruptedException e) {
				break;
			}
		}
		this.simpleGame.setSpeed(3);
		this.stopThread();
	}
	
}
