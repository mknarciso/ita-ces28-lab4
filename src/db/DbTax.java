package db;

import java.util.ArrayList;

import nota_fiscal.ItemDeVenda;
import ps.PS;

public class DbTax {
	// Each Tax is an object of a SubClass of Imposto
	private IPI taxIPI;
	private ICMS taxICMS;
	protected static int itemQtde;
	
	protected DbTax(){
		taxIPI = new IPI(); 
		taxICMS = new ICMS(); 
	}
	
	public double calculateTax(ArrayList<ItemDeVenda> idv) {
		// This method is called once for each NF, so taxes instances are
		// reset, but could keep persistent values 
		resetAll();
		for(int i=0;i<idv.size();i++){
			//Gets IV quantity 
			itemQtde = idv.get(i).getQuantity();
			//Calculate all taxes for each IV
			allTaxes(idv.get(i).getPS());
		}
		//Return all accumulated taxes 
		return sumTaxes();
	}

	private double sumTaxes() {
		return taxIPI.getTotal()+taxICMS.getTotal();
	}
	private void resetAll(){
		taxIPI.reset();
		taxICMS.reset();
	}

	private void allTaxes(PS raiz){
		raiz.aceitarVisitante(taxIPI);
		raiz.aceitarVisitante(taxICMS);
	}
}
