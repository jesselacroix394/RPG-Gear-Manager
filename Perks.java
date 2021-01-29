/*
 *	Code by Jesse Lacroix
 *	May 16, 2019
 *	
 *	Perks.java
 *	**Creates information for each perk**
 */

public class Perks{
	
	private String name;
	private String desc;
	private int value;
	
	public Perks(){
		
			name = "";
			desc = "";
			value = 0;
	}
	
	public Perks(String name, String desc, int value){
		
		this.name = name;
		this.desc = desc;
		this.value = value;
	}
	
	public String getName(){
		
		return name;
	}
	
	public void setName(String name){
		
		this.name = name;
	}
	
	public String getDesc(){
		
		return desc;
	}
	
	public void setDesc(String desc){
		
		this.desc = desc;
	}
	
	public int getValue(){
		
		return value;
	}
	
	public void setValue(int value){
		
		this.value = value;
	}
	
	public String toString(){
		
		String result = System.lineSeparator() + System.lineSeparator() + name + System.lineSeparator() + desc;
		return result;
	}
	
}