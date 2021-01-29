
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	
	public static String helmet = "Helmet";
	public static String chest = "Chestpiece";
	public static String gauntlets = "Gauntlets";
	public static String boots = "Boots";
	
	public static String common = "Common";
	public static String uncommon = "Uncommon";
	public static String rare = "Rare";
	public static String legendary = "Legendary";
	public static String exotic = "Exotic";
	
	
	public GamePanel(){
		
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
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
		 
		 
		
		/*
		FontMetrics metrics = g.getFontMetrics();
		Rectangle2D strRect = metrics.getStringBounds("Destiny", g);
		
		int centX = WIDTH/2;
		int centY = HEIGHT/3;
		
		int strX = centX - (int)(strRect.getWidth()/2);
		int strY = centY + (int)(strRect.getHeight()/2);
		*/
		
		g.drawString(C1.toString(), 320, 200);
	}
	
	
}