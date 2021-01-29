/*
 *	Code by Jesse Lacroix
 *	May 16, 2019
 *	
 *	Stats.java
 *	**Sets Gear values and perks**
 */
 
 public class Stats{
	 
	private int armour;
	private String desc;
	private PerkSet perks;
	
	public Stats(){
		
		armour = 0;
		desc = "";
		perks = new PerkSet();
	}
	
	
	public Stats(int armour, String desc, PerkSet perks){

		this.armour = armour;
		this.desc = desc;
		this.perks = perks;
	}	
	 
	public int getArmour(){
		
		return armour;
	}
	
	public void setArmour(int armour){
		
		this.armour = armour;
	}
	 
	public String getDesc(){
	 
		return desc;
	}
	
	public void setDesc(String desc){
		
		this.desc = desc;
	}
	 
	public PerkSet getPerks(){
		
		return perks;
	}
	
	public void setPerks(PerkSet perks){
		
		this.perks = perks;
	}
	
	public String toString(){
		
		String result = System.lineSeparator() + desc + System.lineSeparator() + System.lineSeparator() + armour + perks;
		return result;
	}
 }