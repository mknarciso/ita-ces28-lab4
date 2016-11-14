package db;

import java.util.ArrayList;

import nota_fiscal.ItemDeVenda;
import ps.PS;

public class DbTax {
	// Each Tax is an object of a SubClass of Imposto
	private IPI imposto1;
	private ICMS imposto2;
	protected static int itemQtde;
	
	public double calculateTax(ArrayList<ItemDeVenda> idv) {
		// This method is called once for each NF, so taxes instances are
		// reset, and could keep persistent values through all NF Tax calculation 
		imposto1 = new IPI(); 
		imposto2 = new ICMS(); 
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
		return imposto1.getTotal()+imposto2.getTotal();
	}

	private void allTaxes(PS raiz){
		raiz.aceitarVisitante(imposto1);
		raiz.aceitarVisitante(imposto2);
	}
}
