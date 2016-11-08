package db;

import java.util.List;

import nota_fiscal.*;

public class DbConnectNF { // Singleton

	   //create an object of SingleObject
	   private static DbConnectNF instance = new DbConnectNF();

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private DbConnectNF(){
		   nfDB = new DbNF(); 
	   }

	   //Get the only object available
	   public static DbConnectNF getInstance(){
	      return instance;
	   }

	   DbNF nfDB;
	   
	   public List<NotaFiscal> getAll(){
		   return nfDB.getAll();
	   }
		public NotaFiscal getNF(int id){
			return nfDB.getNF(id);
		}
		public void persistNF(NotaFiscal newNF){
			nfDB.persistNF(newNF);
		}	   
	   public int generateID(NFBuilder newNF){
		   return nfDB.generateID(newNF);
	   }
}
