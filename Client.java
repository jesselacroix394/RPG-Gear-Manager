/*
 *	Code by Jesse Lacroix
 *	Jan, 9, 2021
 *	
 *	Server.java
 *	**Gets info from Server**
 */
 
 import java.net.*;
 import java.io.*;
 import java.util.Scanner;
 
  
public class Client{
	 
	 //Constant variables user throughout the program
	 private Socket socket = null;
	 private Scanner input = null;
	 private Scanner output = null;
	 private String closed = "";
	 private Thread t1;
	 private Thread t2;
	 
	 //Send message thread, sends a message to server
	 class sendMsg extends Thread{
	
		
		private String threadName;
		private PrintWriter data = null;
	 
		sendMsg(String inName){
		 
			threadName = inName;
			//System.out.println("Created " + threadName);
		}
	
		public void run(){
			//System.out.println("Running " + threadName);
		 
			try{
			 
				input = new Scanner(System.in);
				data = new PrintWriter(socket.getOutputStream(), true);
			 
				String send = "";
			 
				while(!closed.equals("Close")){
				 
					send = input.nextLine(); 
					closed = send;
					data.println(send);				 
				}
				
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
	 
		public void start(){
			//System.out.println("Starting " + threadName); 
			if(t1 == null){
			 
				t1 = new Thread(this, threadName);
				t1.start();
			}
		}	 
	}

	//Receives messages from server
	class receiveMsg extends Thread{
	
		
		private String threadName;
		private Scanner output = null;
	 
		receiveMsg(String inName){
		 
			threadName = inName;
			//System.out.println("Created " + threadName);
		}
	
		public void run(){
			//System.out.println("Running " + threadName);
		 
			try{
			 
				output = new Scanner(socket.getInputStream());
			 
				String receive = "";
			 
				while(!closed.equals("Close")){
				 
					receive = output.nextLine(); 
					closed = receive;
					System.out.println(receive);			 
				}
				
				
				
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
	 
		public void start(){
			//System.out.println("Starting " + threadName); 
			if(t2 == null){
			 
				t2 = new Thread(this, threadName);
				t2.start();
			}
		}	 
	}
	
	//Connects to server
	public Client(String address, int port){
		 
		try{ 
		
			System.out.println("Connecting");
			socket = new Socket(address, port);
			System.out.println("Connected");	 
		}
		catch(UnknownHostException e){
	 
			System.out.println(e);
		}
		catch(IOException e){
			
			System.out.println(e);
		}
		
		System.out.println("Enter username");
		
		receiveMsg T1 = new receiveMsg("receiveMsg");
		T1.start();
		sendMsg T2 = new sendMsg("sendMsg");
		T2.start();
		
	 }
	 
	 public static void main(String[] args){
		 
		Client client = new Client("127.0.0.1", 5000);
		
	 }
	 
 }
 
