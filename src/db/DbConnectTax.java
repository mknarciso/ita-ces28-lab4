package db;

import java.util.ArrayList;
import nota_fiscal.ItemDeVenda;

public class DbConnectTax {
	// create an object of SingleObject
	private static DbConnectTax instance = new DbConnectTax();

	// make the constructor private so that this class cannot be
	// instantiated
	private DbConnectTax() {
		taxDB = new DbTax();
	}

	// Get the only object available
	public static DbConnectTax getInstance() {
		return instance;
	}

	public double calculateTax(ArrayList<ItemDeVenda> idv) {
		return taxDB.calculateTax(idv);
	}

	DbTax taxDB;

}
