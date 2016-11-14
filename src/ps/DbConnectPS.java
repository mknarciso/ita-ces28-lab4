package ps;

import java.util.List;

public class DbConnectPS { // Singleton

	   //create an object of SingleObject
	   private static DbConnectPS instance = new DbConnectPS();

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private DbConnectPS(){
		   psDB = new DbPS(); 
	   }

	   //Get the only object available
	   public static DbConnectPS getInstance(){
	      return instance;
	   }

	   private DbPS psDB;
	   
	   public List<PS> getAll(){
		   return psDB.getAll();
	   }
	   
	   public void addPS(PS newProduct){
		   psDB.addPS(newProduct);
	   }
	   public PS getPS(String name){
		   return psDB.getPS(name);
	   }
}

