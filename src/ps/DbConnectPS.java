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
	   
	   public void newPS(String type,String nome, float preco, String setor, int categoriaTributaria, String outros){
		   psDB.newPS(type, nome, preco, setor, categoriaTributaria, outros);
	   }
	   
	   public void addToPSSubPS(String main, String sub) throws Exception{
		   psDB.addToPSSubPS(main,sub);
	   }
	   
	   public PS getPS(String name){
		   return psDB.getPS(name);
	   }
}

