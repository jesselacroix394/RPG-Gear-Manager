/*
 *	Code by Jesse Lacroix
 *	May 16, 2019
 *	
 *	PerkSet.java
 *	**Creates Perks for each gear piece**
 */

public class PerkSet{
	
	private Perks perk1;
	private Perks perk2;
	
	public PerkSet(){
		
		perk1 = new Perks();
		perk2 = new Perks();
	}
	
	public PerkSet(Perks perk1, Perks perk2){
		
		this.perk1 = perk1;
		this.perk2 = perk2;
	}
	
	public Perks getPerk1(){
		
		return perk1;
	}
	
	public void setPerk1(Perks perk1){
		
		this.perk1 = perk1;
	}
	
	public Perks getPerk2(){
		
		return perk2;
	}
	
	public void setPerk2(Perks perk2){
		
		this.perk2 = perk2;
	}
	
	public String toString(){
		
		String result = perk1 + "\t\t" + perk2;
		return result;		
	}
	
}