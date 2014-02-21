package graphic;

import java.math.BigDecimal;

public class SpeedBoost extends Thread
{
	private Player player;
	private Thread T;
	private long startTime;
	private int boostFuel;
	
	FuelRegeneration fuelRegeneration;
	
	final public int MAX_FUEL = 10;
	
	SpeedBoost(Player player)
	{
		this.player=player;
		fuelRegeneration = new FuelRegeneration(this);
		this.boostFuel=MAX_FUEL;
		this.startTime=0;
		T=null;
		
		fuelRegeneration.init();
	}
	
	public boolean isActive()
	{
		return (T==null?false:true);
	}
	
	public int getBoostFuel()
	{
		return this.boostFuel;
	}
	
	public void useBoostFuel(int fuelUsed)
	{
		if(this.boostFuel-fuelUsed<0)
			this.boostFuel=0;
		else
			this.boostFuel-=fuelUsed;
		
	}
	
	public void regenerateBoostFuel(int fuelRegenerated)
	{
		this.boostFuel+=fuelRegenerated;
	}
	
	public void init()
	{
		if(player.flyingUp.isActive())
		{
			T = new Thread(this);
			T.start();
		}
		/*
		if(!fuelRegeneration.isActive())
			fuelRegeneration.init();*/
	}
	
	public void stopThread()
	{
		if(T!=null)
		{
			T.interrupt();
			T = null;
		}
	}

	public void run()
	{
		player.flyingUp.setSpeed(2);
		player.setState(2);
		this.useBoostFuel(1);
		
		startTime = System.currentTimeMillis();
		long endTime=0;
		
		while(true)
		{
			endTime  = System.currentTimeMillis();
			double passedTime =(double)(endTime-startTime)/1000;
			
			if(passedTime==0.05 || passedTime==0.1|| passedTime==0.15 || passedTime==0.2 ||passedTime==0.25||passedTime==0.3
					||passedTime==0.35||passedTime==0.4||passedTime==0.45||passedTime==0.5)
				if(this.getBoostFuel()>0)
					this.useBoostFuel(1);
				
			if(this.getBoostFuel()==0)
				break;
			
			try{
				player.speedBoost.sleep(1);
			}catch (InterruptedException e) {
				break;
			}
		}
		player.flyingUp.setSpeed(1);
		
		/*
		 * set the proper state of player
		 */
		if(player.flyingUp.isActive())
			player.setState(1);
		else
			player.setState(0);
		/*
		 * make sure that the thread is stoped after the fuel reserve has been depleted
		 */
		this.stopThread();
	}
	
}