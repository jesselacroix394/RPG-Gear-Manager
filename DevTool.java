/*
 *	Code by Jesse Lacroix
 *	Feb. 2, 2021
 *	
 *	DevTool.java
 *	**Allows for creation of items and perks**
 */
 
 import java.net.*;
 import java.io.*;
 import java.util.*;
 import java.nio.file.*;
 
 public class DevTool{
	 
	 //Foundation for future sorting
	 //LinkedList<String> items = new LinkedList();
	 //LinkedList<String> perks = new LinkedList();
	 	 
	 static File itemFile = null;
	 static File perkFile = null;
	 
	 /*
	  * addToFile(File, String)
	  * Adds created items and perks to their files
	  * Returns nothing
	  */
	 public static void addToFile(File filePath, String msg){
		 
		 try{
			FileWriter adding = new FileWriter(filePath, true);
			BufferedWriter buffer = new BufferedWriter(adding);
			PrintWriter out = new PrintWriter(buffer);
		 
			out.println(msg);		 
			
			out.close();
			buffer.close();
			adding.close();
			
		 }
		 catch(IOException e){
			 
			 System.out.println(e);
		 }
	 }
	 
	 /*
	  * addItem()
	  * Gets required information from cammand line to create item 
	  * Returns nothing
	  */
	 public static void addItem(){
				 
		Scanner input = new Scanner(System.in);
		 
		String fullItem = "";
		String rarity = "";
	 
		System.out.println("Input item name:");
		fullItem += input.nextLine() + "$";
		
		System.out.println("Input item type:");
		fullItem += input.nextLine() + "$";
		
		
		System.out.println("Input item rarity:");
		rarity = input.nextLine();
		fullItem +=  rarity + "$";
		
		System.out.println("Input item level:");
		fullItem += input.nextLine() + "$";
		
		System.out.println("Input item description:");
		fullItem += input.nextLine();
		
		if(rarity.equals("Exotic")){
			
			System.out.println("Input perk 1 name:");
			fullItem +=  "$" + input.nextLine() + "$";
		
			System.out.println("Input perk 1 description:");
			fullItem += input.nextLine() + "$";
		
			System.out.println("Input perk 1 amount:");
			fullItem += input.nextLine() + "$";
			
			System.out.println("Input perk 2 name:");
			fullItem += input.nextLine() + "$";
		
			System.out.println("Input perk 2 description:");
			fullItem += input.nextLine() + "$";
		
			System.out.println("Input perk 2 amount:");
			fullItem += input.nextLine();
			
		}
		
		addToFile(itemFile, fullItem);
	 }
	 
	 /*
	  * addPerk()
	  * Gets required information from cammand line to create perk
	  * Returns nothing
	  */
	 public static void addPerk(){
		 
		Scanner input = new Scanner(System.in);
		 
		String fullPerk = "";
	 
		System.out.println("Input perk name:");
		fullPerk += "$" + input.nextLine() + "$";
		
		System.out.println("Input perk description:");
		fullPerk += input.nextLine() + "$";
		
		System.out.println("Input perk amount:");
		fullPerk += input.nextLine();
		
		addToFile(perkFile, fullPerk);
	 }
	 
	 /*
	  * getType()
	  * Checks users input for which type to use
	  * Returns nothing
	  */
	 public static void getType(String type){
		 
		if(type.equals("Item")){
			
			addItem();
		}
		else if(type.equals("Perk")){
			
			addPerk();
		}
		else{
			
			System.out.println("Not a valid type");
		}
	 }
	 	 
	 public static void main(String[] args){
	 
		String type = "";
		Scanner start = new Scanner(System.in);
		
		itemFile = new File("items/items.txt");
		perkFile = new File("items/weapon_perks.txt");
		
	 
		while(true){
			
			System.out.println("What would you like to add? (Item/Perk)");
			
			type = start.nextLine();
			if(type == "Close"){
				
				break;
			}			
			getType(type);
		}		
	 }
 }
 