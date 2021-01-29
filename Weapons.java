/*
 *	Code by Jesse Lacroix
 *	May 16, 2019
 *	
 *	Weapons.java
 *	**Set character weapons: Heavy, Special, Primary**
 */
 
 public class Weapons{
	 
	 private Gear heavy;
	 private Gear special;
	 private Gear primary;
	 
	 public Weapons(){
		 
		heavy = new Gear();
		special = new Gear();
		primary = new Gear();
	 }
	 
	 public Weapons(Gear heavy, Gear special, Gear primary){
		 
		this.heavy = heavy;
		this.special = special;
		this.primary = primary;
	 }
	 
	 public Gear getHeavy(){
		 
		 return heavy;
	 }
	 
	 public void setHeavy(Gear Heavy){
		 
		 this.heavy = heavy;
	 }
	 
	 public Gear getSpecial(){
		 
		 return special;
	 }
	 
	 public void setSpecial(Gear special){
		 
		 this.special = special;
	 }
	 
	 public Gear getPrimary(){
		 
		 return primary;
	 }
	 
	 public void setPrimary(Gear primary){
		 
		 this.primary = primary;
	 }
	 
	 public String toString(){
		 
		 String result = System.lineSeparator() + primary + System.lineSeparator() + System.lineSeparator() + special + System.lineSeparator() + System.lineSeparator() + heavy + System.lineSeparator();
		 return result;
	 }
 }