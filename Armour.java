/*
 *	Code by Jesse Lacroix
 *	May 16, 2019
 *	
 *	Armour.java
 *	**Sets armour helmet, chestpiece, gauntlets and boots**
 */
 
 public class Armour{
	 
	 private Gear helm;
	 private Gear chest;
	 private Gear gauntlets;
	 private Gear boots;
	 
	 
	 public Armour(){
		 
		 helm = new Gear();
		 chest = new Gear();
		 gauntlets = new Gear();
		 boots = new Gear();
	 }
	 
	 public Armour(Gear helm, Gear chest, Gear gauntlets, Gear boots){
		 
		 this.helm = helm;
		 this.chest = chest;
		 this.gauntlets = gauntlets;
		 this.boots = boots;
	 }
	 
	 public Gear getHelm(){
		 
		return helm;
	 }
	 
	 public void setArmour(Gear helm){
		 
		 this.helm = helm;
	 }
	 
	 public Gear getChest(){
		 
		 return chest;
	 }
	 
	 public void setChest(Gear chest){
		 
		 this.chest = chest;
	 }
	 
	 public Gear getGauntlets(){
		 
		return gauntlets;
	 }
	 
	 public void setGauntlets(Gear gauntlets){
		 
		this.gauntlets = gauntlets;
	 }
	 
	 public Gear getBoots(){
		 
		return boots;
	 }
	 
	 public void setBoots(Gear boots){
		 
		this.boots = boots;
	 }
	 
	 public String toString(){
		 
		String result = System.lineSeparator() + helm + System.lineSeparator() + System.lineSeparator() + chest + System.lineSeparator() + System.lineSeparator() + gauntlets + System.lineSeparator() + System.lineSeparator() + boots + System.lineSeparator();
		return result;
	 }
 }