package graphic;

public class SpeedBoost extends Thread
{
	Player player;
	int boostLength;
	
	SpeedBoost(Player player)
	{
		this.player=player;
		boostLength=5;
	}
	
	public void run()
	{
		player.flyingUp.setSpeed(2);

		while(true)
		{
			try{
				player.speedBoost.sleep(5);
			}catch (InterruptedException e) {
				break;
			}
		}
		player.flyingUp.setSpeed(1);
		
	}
	
}