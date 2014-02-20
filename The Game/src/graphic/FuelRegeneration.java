package graphic;

public class FuelRegeneration extends Thread {
	Thread T;
	SpeedBoost speedBoost;
	
	FuelRegeneration(SpeedBoost speedBoost)
	{
		this.speedBoost=speedBoost;
		T = null;
	}
	
	public boolean isActive()
	{
		return (T==null?false:true);
	}
	
	void init()
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
		long endTime=0;
		
		while(true)
		{
			try {
				T.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			double passedTime =(double)(endTime-startTime)/1000;
			if(!speedBoost.isActive())
			{
				if(this.speedBoost.getBoostFuel()<this.speedBoost.MAX_FUEL)
				{
					this.speedBoost.regenerateBoostFuel(1);
				}
			}
			
		}
	}
}
