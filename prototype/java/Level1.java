import java.util.Vector;

//Game Level1
public class Level1 {
	private Monster defaultMonsterPrototype = null;
	public Level1()
	{
		//Build a prototype that is used to create new monsters by
		Monster axeMonster = new Monster();
		axeMonster.setWeapon("Axe");
		defaultMonsterPrototype = axeMonster;
	}
	
	private Monster getNewMonster()
	{
		return defaultMonsterPrototype.clone();
	}
	
	public void run()
	{
		Vector<Monster> monstersAlive = new Vector<Monster>();
		
		for (int i= 0; i<5; i++)
		{
			monstersAlive.add(getNewMonster());
			System.out.println("New Monster Created from Prototype");
		}
	}
}
