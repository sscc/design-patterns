//Game Monster Class that can be used as a "prototype" for other monsters
public class Monster {
	private String weapon = "";
	
	public Monster()
	{
		
	}
	
	public void setWeapon(String newWeapon)
	{
		this.weapon = newWeapon;
	}
	
	public String getWeapon()
	{
		return this.weapon;
	}
	
	//Clone method makes this class support being a "prototype"
	public Monster clone()
	{
		Monster newClone = new Monster();
		newClone.setWeapon(this.getWeapon());
		return newClone;
	}
}
