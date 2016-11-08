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
		public void persistNF(NotaFiscal newNF) throws NFAlreadyValidatedException {
			if (!nfDB.existsNF(newNF.getId()))
				nfDB.persistNF(newNF);
			else throw new NFAlreadyValidatedException();
		}	   
	   public int generateID(NFBuilder newNF) throws NFAlreadyValidatedException{
		   if (newNF.isValidated())
			   return nfDB.generateID(newNF);
		   else throw new NFAlreadyValidatedException();
	   }
}
