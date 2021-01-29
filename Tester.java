/*
 *	Code by Jesse Lacroix
 *	May 16, 2019
 *	
 *	Tester.java
 *	**Tests the Gear and Character**
 */
 
 import java.util.Scanner;
 
 
 public class Tester{
	 
	public static String helmet = "Helmet";
	public static String chest = "Chestpiece";
	public static String gauntlets = "Gauntlets";
	public static String boots = "Boots";
	
	public static String common = "Common";
	public static String uncommon = "Uncommon";
	public static String rare = "Rare";
	public static String legendary = "Legendary";
	public static String exotic = "Exotic";
	 
	 public static void main(String[] args){
		 
		 Perks Perks1 = new Perks("True Vision", "Increases accuracy.", 10);
		 Perks Perks2 = new Perks("Sharp Blades", "Increases Melee damage.", 10);
		 Perks Perks3 = new Perks("Well Oiled", "Increase rate of fire.", 10);
		 Perks Perks4 = new Perks("Bloodlust", "Increases damage.", 10);
		 
		 PerkSet PerkSet1 = new PerkSet(Perks1, Perks2);
		 PerkSet PerkSet2 = new PerkSet(Perks3, Perks4);
		 
		 Stats Desc1 = new Stats(255, "Found surrounded by bones in an empty desert on Mars.", PerkSet1);
		 Stats Desc2 = new Stats(255, "A faint signal still pings to its lost brother, somewhere on the outer core.", PerkSet2);
		 Stats Desc3 = new Stats(300, "She held on for a long time but the storm was too strong.", PerkSet1);
		 Stats Desc4 = new Stats(255, "As the ground burnt my feet, my feet burned the ground.", PerkSet2);
		 
		 Stats Desc5 = new Stats(300, "The flames burnt away my sin and left me pure. - Baron Tagal, The First Burnt", PerkSet2);
		 Stats Desc6 = new Stats(255, "They never heard a thing.", PerkSet2);
		 Stats Desc7 = new Stats(255, "Take up arms Brothers and Sister, our time is now.", PerkSet2);
		 
		 Gear Helm = new Gear("Vision of the Walker", helmet, legendary, Desc1);
		 Gear Chest = new Gear("Blood oath", chest, legendary, Desc2);
		 Gear Gauntlets = new Gear("Sanara's Grasp", gauntlets, exotic, Desc3);
		 Gear Boots = new Gear("Blazing Steps", boots, legendary, Desc4);
		 
		 Gear Heavy = new Gear("The InSINerator", "Rocket Launcher", exotic, Desc5);
		 Gear Special = new Gear("Dead Silence", "Sniper Rifle", legendary, Desc6);
		 Gear Primary = new Gear("Call to Arms", "Revolver", legendary, Desc7);
		 
		 Armour A1 = new Armour(Helm, Chest, Gauntlets, Boots);
		 Weapons W1 = new Weapons(Heavy, Special, Primary);

		 Character C1 = new Character(A1, W1);
		 
		 String result = C1.getWeapons().toString();
		 System.out.println(result);
		 
		 int value;
		 value = C1.getWeapons().getHeavy().getDesc().getPerks().getPerk1().getValue();
		 
		 int damage = C1.getWeapons().getHeavy().getDesc().getArmour();
		 
		 System.out.println(damage);
		 
		 damage += value;
		 
		 C1.getWeapons().getHeavy().getDesc().setArmour(damage);
		 
		 System.out.println(C1);
		 
		 Character C2 = new Character(new Armour(new Gear("Dream of Teeth", helmet, legendary, new Stats(255, "The teeth flow like a river, behold my wrath", new PerkSet(new Perks("Bloodless", "Cannot be bled", 10), new Perks("Sharp Bite", "Cripples armour", 10)))),
		 Chest, Gauntlets, Boots), W1);
		 
		 System.out.println(C2);
		 
		 /*
		 Perks [] array1 = {Perks1, Perks2};
		 
		 System.out.println(array1[1]);
		 */
		 
	 }
 }
