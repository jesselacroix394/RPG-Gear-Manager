/*
 *	Code by Jesse Lacroix
 *	May 16, 2019
 *	
 *	Gear.java
 *	**Sets Gear name, type and rarity**
 */
 
public class Gear{
	
	private String name;
	private String type;
	private String rarity;
	private Stats desc;
	
	
	public Gear(){
		
		name = "";
		type = "";
		rarity = "";
		desc = new Stats();
	}
	
	public Gear(String name, String type, String rarity, Stats desc){
		
		this.name = name;
		this.type = type;
		this.rarity = rarity;
		this.desc = desc;
	}
	
	
	public String getName(){
		
		return name;
	}
	
	
	public void setName(String name){
		
		this.name = name;
	}
	
	public String getType(){
		
		return type;
	}
	
	public void setType(String type){
		
		this.type = type;
	}
	
	public String getRarity(){
		
		return rarity;
	}
	
	public void setRarity(String rarity){
		
		this.rarity = rarity;
	}
	
	public Stats getDesc(){
		
		return desc;
	}
	
	public void setDesc(Stats desc){
		
		this.desc = desc;
	}
	
	public String toString(){
	
		String result = name + System.lineSeparator() + type + System.lineSeparator() + rarity + System.lineSeparator() + desc;
		return result;
	}
}