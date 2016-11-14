package db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nota_fiscal.*;

public class DbNF {
	private int _id = 201600000;
	private List<NotaFiscal> nfs = new ArrayList<NotaFiscal>();
	
	public List<NotaFiscal> getAll() {
		return Collections.unmodifiableList(nfs);
	}
	public NotaFiscal getNF(int id){
		for(int i=0;i<nfs.size();i++){
			if (nfs.get(i).getId()==id){
				return nfs.get(i);
				}
		}
		return null;
	}
	public void persistNF(NotaFiscal newNF){
		nfs.add(newNF);
	}
	
	public int generateID(NFBuilder newNF) {
		// Mock for the unique id generator
		_id++;
		return _id;
	}
	
	public boolean existsNF(int id) {
		// See if the NF already exists
		for (int i = 0; i < nfs.size(); i++) {
			if (nfs.get(i).getId() == id)
				return true;			
		}
		return false;
	}

}
