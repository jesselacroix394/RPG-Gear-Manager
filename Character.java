/*
 *	Code by Jesse Lacroix
 *	May 16, 2019
 *	
 *	Character.java
 *	**Set character armour and weapons**
 */
 
public class Character{
	
	private Armour armour;
	private Weapons weapons;
	
	public Character(){
		
		armour = new Armour();
		weapons = new Weapons();
	}
	
	public Character(Armour armour, Weapons weapons){
		
		this.armour = armour;
		this.weapons = weapons;
	}
	
	public Armour getArmour(){
		
		return armour;
	}
	
	public void setArmour(Armour armour){
		
		this.armour = armour;
	}
	
	public Weapons getWeapons(){
		
		return weapons;
	}
	
	public void setWeapons(Weapons weapons){
		
		this.weapons = weapons;
	}
	
	public String toString(){
		
		String result = armour + System.lineSeparator() + System.lineSeparator() + System.lineSeparator() + weapons;
		return result;
	}
}