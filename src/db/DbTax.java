package db;

import java.util.ArrayList;

import nota_fiscal.ItemDeVenda;
import ps.PS;

public class DbTax {

	public float calculateTax(ArrayList<ItemDeVenda> idv) {
		float total =0;
		for(int i=0;i<idv.size();i++){
			total = total + allTaxes(idv.get(i).getPS());
		}
		return total;
	}

	private float allTaxes(PS raiz){
		float total =0;
		
		return total;
	}
}
