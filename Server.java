/*
 *	Code by Jesse Lacroix
 *	Jan, 9, 2021
 *	
 *	Server.java
 *	**Sends server info to clients**
 */
  
 import java.net.*;
 import java.io.*;
 import java.util.*;
 import java.nio.file.*;
 
 public class Server{
	 
	 //Constant variables user throughout the program
	 static final int port = 5000;
	 static final int NAME = 0;
	 static final int TYPE = 1;
	 static final int RARITY = 2;
	 static final int STAT = 3;
	 static final int DESC = 4;
	 static final int PERK1NAME = 5;
	 static final int PERK1DESC = 6;
	 static final int PERK1VAL = 7;
	 static final int PERK2NAME = 8;
	 static final int PERK2DESC = 9;
	 static final int PERK2VAL = 10;
	 
	 private Socket socket = null;
	 
	 //Server threading class, handles multiple clients
	 class multiClients extends Thread{
	 
		//Private variables used by multiClients
		private Scanner input = null;
		private Scanner output = null;
		private PrintWriter data = null;
		private String closed = "";
		private Thread t1;
		private Thread t2;
		private Thread serv;
	 
		//Send message thread, sends a message to the client (Issues with multiple clients connected)
		class sendMsg extends Thread{
	
			//Private variables used in sendMsg
			private String threadName;
		
			//Thread constructor
			sendMsg(String inName){
		 
				threadName = inName;
				//System.out.println("Created " + threadName);
			}
	
			//Thread body
			//Allows server to send messages to clients, closes server if sent message is "Close"
			public void run(){
				//System.out.println("Running " + threadName);
		 
				try{
					
					//Reads input from system
					input = new Scanner(System.in);
					//Sends data to socket output
					data = new PrintWriter(socket.getOutputStream(), true);
					
					//Creates empty send string
					String send = "";
					
					//Sends messages until "Close"
					while(!closed.equals("Close")){
				 
						send = input.nextLine();
						closed = send;
						data.println(send);				 
					}
					
					//Closes socket
					try{
						socket.close();
						System.exit(0);
					}
					catch(IOException e){
		
						System.out.println(e);
					}
				}
				catch(IOException e){
			 
					System.out.println("Thread " + threadName + " interrupted"); 
				}
		 
				//System.out.println("Finished thread " + threadName);
			}
	 
			//Creates new thread
			public void start(){
				//System.out.println("Starting " + threadName); 
				if(t1 == null){
					
					t1 = new Thread(this, threadName);
					t1.start();
				}
			}	 
		}

		//Receives messages and processes the information
		class receiveMsg extends Thread{
	
			//Private variables used by receiveMsg
			private String threadName;
			private File user;
			private File temp;
			private LinkedList<Gear> items = new LinkedList<Gear>();
			private LinkedList<String> weaponPerks = new LinkedList<String>();
			//private String[] armourPerks;
			private String[] splitItems;
			private Gear gear;
			private Gear dispGear;
			private PerkSet perkSet;
			private Scanner fileRead;
			private Scanner tempRead;
			private FileWriter adding;
			private BufferedWriter buffer;
			private PrintWriter out;
		
			/* receiveMsg
			 * Takes String, File, File, File
			 * Gets infromation from user and item files
			 * Returns nothing
			 */
			receiveMsg(String inName, File userFile, File itemFile, File weaponPerkFile){
		 
				threadName = inName;
				user = userFile;
				temp = new File("users/" + inName +"temp.txt");
				
								
				try{
					fileRead = new Scanner(itemFile);
				}
				catch(FileNotFoundException e){
					
					System.out.println(e);
				}
				
				while(fileRead.hasNextLine()){
					String inItems = fileRead.nextLine();
					
					//System.out.println(inItems);
					
					splitItems = inItems.split("\\$");
					
					//System.out.println(splitItems[0]);
					
					if(!splitItems[RARITY].equals("Exotic")){
						
						perkSet = new PerkSet();
					}
					else{
						perkSet = new PerkSet(new Perks(splitItems[PERK1NAME], splitItems[PERK1DESC], Integer.valueOf(splitItems[PERK1VAL])), new Perks(splitItems[PERK2NAME], splitItems[PERK2DESC], Integer.valueOf(splitItems[PERK2VAL])));
					}					
					
					gear = new Gear(splitItems[NAME], splitItems[TYPE], splitItems[RARITY], new Stats(Integer.valueOf(splitItems[STAT]), splitItems[DESC], perkSet));
					
					items.add(gear);
					
				}
				fileRead.close();
				
				try{
					fileRead = new Scanner(weaponPerkFile);
				}
				catch(FileNotFoundException e){
					
					System.out.println(e);
				}
				
				while(fileRead.hasNextLine()){
					
					weaponPerks.add(fileRead.nextLine());
				}
				
				//System.out.println("Created " + threadName);
			}
			
			/* containsName
			 * Takes LinkedList<Gear>, String
			 * Checks if item index contains item with input name
			 * Returns boolean
			 */
			public boolean containsName(LinkedList<Gear> items, String name){
				
				for(int i = 0; i < items.size(); i++){
					
					if(items.get(i).getName().equals(name)){
						return true;
					}					
				}
				
				return false;
			}
			
			/* findName
			 * Takes LinkedList<Gear>, String
			 * Finds item in item index and returns it
			 * Returns Gear
			 */
			public Gear findName(LinkedList<Gear> items, String name){
				
				for(int i = 0; i < items.size(); i++){
					
					if(items.get(i).getName().equals(name)){
						return items.get(i);
					}					
				}
				
				return null;
			}			
			
			/* display
			 * Takes String
			 * Displays the gear piece if the user has it
			 * Returns String
			 */
			public String display(String name){
				
				String output = "";
				
				try{
					fileRead = new Scanner(user);
				
					while(fileRead.hasNextLine()){
						
						splitItems = fileRead.nextLine().split("\\$");
						
						if(name.equals(splitItems[NAME]) && containsName(items, name)){
							
							dispGear = findName(items, name);
							
							if(!dispGear.getRarity().equals("Exotic")){
								perkSet = new PerkSet(new Perks(splitItems[1], splitItems[2], Integer.valueOf(splitItems[3])), new Perks(splitItems[4], splitItems[5], Integer.valueOf(splitItems[6])));
							
								dispGear.getDesc().setPerks(perkSet);
							}	

							output = dispGear.toString();
							fileRead.close();
							return output;
						}
					}
				}
				catch(FileNotFoundException e){
					
					System.out.println(e);
				}
				fileRead.close();
				return "Item not found";
				
			}
			
			/* randomPerks
			 * Takes nothing
			 * Generates a random perk from the perk pool
			 * Returns String
			 */
			public String randomPerks(){
				
				Random rand = new Random();
				int i = rand.nextInt(weaponPerks.size());
				return weaponPerks.get(i);
			}
			
			/* add
			 * Takes String
			 * Adds an item to the users account
			 * Returns String
			 */
			public String add(String name){
				
				try{
					
					fileRead = new Scanner(user);
				
					while(fileRead.hasNextLine()){
						if(name.equals(fileRead.nextLine().split("\\$")[0])){
							
							fileRead.close();
							return "Item already added";
						}
					}
				}
				catch(FileNotFoundException e){
					
					System.out.println(e);
				}
				
				if(containsName(items, name)){
					try{
						adding = new FileWriter(user, true);
						buffer = new BufferedWriter(adding);
						out = new PrintWriter(buffer);
						
						if(!findName(items, name).getRarity().equals("Exotic")){
							out.println(name + randomPerks() + randomPerks());
						}
						else{
							out.println(name);
						}
						
						
						out.close();
						buffer.close();
						adding.close();
						fileRead.close();
					}
					catch(IOException e){
					
						System.out.println(e);
					}
					return "Adding " + name;
				}
				else{
					return "Item doesn't exist";
				}
				
			}
			
			/* remove
			 * Takes String
			 * Removes a item from the users account
			 * Returns String
			 */
			public String remove(String name){
				
				String output = "";
				
				try{
					
					temp.createNewFile();
					fileRead = new Scanner(user);
					tempRead = new Scanner(temp);
					
					adding = new FileWriter(temp, true);
					buffer = new BufferedWriter(adding);
					out = new PrintWriter(buffer);
					
					while(fileRead.hasNextLine()){

						String copyLine = fileRead.nextLine();
						 
						splitItems = copyLine.split("\\$");
							
						if(!name.equals(splitItems[0])){
								
							out.println(copyLine);
						}
						else{
								
							output = "Removing " + splitItems[0];
						}
					}
					
					out.close();
					buffer.close();
					adding.close();
					tempRead.close();
					fileRead.close();
					
					new File("users/" + threadName +".txt").delete();
					
					boolean success = temp.renameTo(new File("users/" + threadName +".txt"));
					
					if(!success){
						
						System.out.println("Could not rename");
					}
					
				}
				catch(FileNotFoundException e){
					
					System.out.println(e);

				}
				catch(IOException e){
				
					System.out.println(e);
				}
				
				if(output.equals("")){
					output = name + " not found";
				}	

				return output;
			}
			
			/* list
			 * Takes nothing
			 * Displays a list of all items
			 * Returns String
			 */
			public String list(){
				
				String fullList = "";
				String[] splitLines;
				try{
					
					fileRead = new Scanner(user);
				
					while(fileRead.hasNextLine()){
						
						splitLines = fileRead.nextLine().split("\\$");

						fullList += splitLines[0] + "\n";
					}
					
					fileRead.close();
				}
				catch(FileNotFoundException e){
					
					System.out.println(e);
				}
				
				fullList = fullList.substring(0, fullList.length() - 1);
				
				return fullList;
			}
			
			
			//Receives messages from the client and processes them
			public void run(){
				//System.out.println("Running " + threadName);
				
				String[] request;
		 
				try{
					
					output = new Scanner(socket.getInputStream());
					input = new Scanner(System.in);
					data = new PrintWriter(socket.getOutputStream(), true);
			 
					String receive = "";
					String name = "";
					String send = "";
			 
					//Receives messages until the client closes
					while(!closed.equals("Close")){
				 
						receive = output.nextLine(); 
						closed = receive;
											
						request = receive.split(" ");
						
						//System.out.println(receive);
						
						for(int i = 0; i < request.length - 1; i++){
							
							name += request[i];
							
							if(i != request.length - 2){
								
								name += " ";
							}
						}
						
						//Adds gear on request
						if(request[request.length - 1].equals("add")){
							
							send = add(name);
						}
						//Displays gear on request
						else if(request[request.length - 1].equals("display")){
							
							send = display(name);
						}
						//Removes gear on request 
						else if(request[request.length - 1].equals("remove")){
							
							send = remove(name);
						}
						//Doesn't really do anything yet...
						else if(request[request.length - 1].equals("equip")){
							
							send = "Equiping " + name;
						}
						else if(request[0].equals("list")){
							
							send = list();
						}
						//Shows which user disconnected
						else if(request[request.length - 1].equals("Close")){
							System.out.println(threadName + " disconnected.");
						}
						//Catches all invalid commands
						else{
							send = request[request.length - 1] + " is not a valid command";
						}
						
						data.println(send);
						
						name = "";
					}					
				}
				catch(IOException e){
			 
					System.out.println("Thread " + threadName + " interrupted"); 
				}
		 
				//System.out.println("Finished thread " + threadName);
			}
	 
			public void start(){
				//System.out.println("Starting " + threadName); 
				if(t2 == null){
			 
					t2 = new Thread(this, threadName);
					t2.start();
				}
			}	 
		}
	
		private String threadName;
	
		/* multiClients
		 * Takes String
		 * Sets threads name
		 * Returns nothing
		 */
		multiClients(String inName){
			
			threadName = inName;
		}
		
		//Sets up each client connection
		public void run(){
					
			try{
				data = new PrintWriter(socket.getOutputStream(), true);
				output = new Scanner(socket.getInputStream());
				String receive = "";
				
				receive = output.nextLine();
				
				//Gets clients ip
				InetSocketAddress socketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
				InetAddress inAddress = socketAddress.getAddress();
				Inet4Address in4Address = (Inet4Address) inAddress;
				String ip4string = in4Address.toString();
			
				ip4string.substring(1);
			
				//Displays username and ip to server log
				System.out.println("Username: " + receive + " Connected from: " + ip4string);
				
				//Finds users file or generates a new one if a new user
				File userFile = new File("users/" + receive + ".txt");
				userFile.createNewFile();
				
				//Sets the item and perk file locations
				File itemFile = new File("items/items.txt");
				File weaponPerkFile = new File("items/weapon_perks.txt");
				
				receiveMsg T1 = new receiveMsg(receive, userFile, itemFile, weaponPerkFile);
				T1.start();
				sendMsg T2 = new sendMsg("sendMsg");
				T2.start();
				
			}
			catch(IOException e){
				System.out.println(e);
			}
			
		}
		
		
		public void start(){
			if(serv == null){
				
				serv = new Thread(this, threadName);
				serv.start();
			}
		}
	}
	
	//Starts the multiClients thread
	public Server(ServerSocket server){
		
		try{
			socket = server.accept();
			
			multiClients newClient = new multiClients("Client");
			newClient.start();
		}
		catch(IOException e){
				System.out.println(e);
		}
	}
	 
	 public static void main(String args[]){
		 
		 ServerSocket server = null;
		 
		 try{
			server = new ServerSocket(port);
		 }
		 catch(IOException e){
				System.out.println(e);
		 }
		 
		 System.out.println("Server online...");
		 
		 //Allows multiple clients to connect
		 while(true){
			new Server(server);
		 }
	 }
 }