/*
 *	Code by Jesse Lacroix
 *	Feb, 9, 2021
 *	
 *	Launcher.cpp
 *	**Launches the main code of the project**
 */
 
 #include <iostream>
 #include <string>
 #include <fstream>
 #include <dirent.h>
 
 using namespace std;
 
 
 /*
  * getChangeLog()
  * Gets the name of the most recent changelog version
  * returns string
  */
 string getChangeLog(){
	 
	string version = "";
	DIR *d;
	struct dirent *dir;
	
	if((d = opendir("changelogs/")) != NULL){
		
		while((dir = readdir(d)) != NULL){
			
			version = dir->d_name;
		}
		closedir(d);
	}
	else{
		
		version = "Could not find version";
		printf("Change logs not found");
	}
	 
	return version;	 
 }
 
 
 int main(void){
	 
	string version = getChangeLog();
	string option;
	string changelog;
	string path;
	
	// Removes the .txt from the version file name
	version = version.substr(0, version.size() - 4);
	
	// Outputs the header for the launcher
	cout << "====================" << endl;
	cout << "RPG Gear Manager" << endl;
	cout << "By Jesse Lacroix" << endl;
	cout << version << endl;
	cout << "====================" << endl << endl;
	
	// Outputs valid commands for the launcher
	cout << "Launch options: (Host/Client/DevTool/ChangeLog)" << endl;
	
	
	while(true){
		
		//Takes users command
		cin >> option;
		
		if(option == "Host"){
			
			//runs Server.java
			system("java Server");
		}
		else if(option == "Client"){
			
			//runs Client.java
			system("java Client");
		}
		else if(option == "DevTool"){
			
			//runs DevTool.java 
			system("java DevTool");
		}
		else if(option == "ChangeLog"){
			
			//Reads the most recent changelog file
			path = "changelogs/" + getChangeLog();
			
			ifstream file(path);
			
			if(file.is_open()){
				
				cout << file.rdbuf() << endl;
			}
			else{
				
				cout << "Could not open file" << endl;
			}
		}		
		else{
			
			cout << "Not a valid command" << endl;
		}
	}
 }